package com.ranchopro.journalApp.controller;
import com.ranchopro.journalApp.entity.JournalEntry;
import com.ranchopro.journalApp.entity.User;
import com.ranchopro.journalApp.service.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import com.ranchopro.journalApp.service.UserService;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/journal")
public class JournalEntryController {

  // injection the  Service class Bean into tjeController so controller will call service from here
    @Autowired
    private JournalEntryService journalEntryService;


    @Autowired
    private UserService userService;


    // get request to that /journal url
    @GetMapping
    public ResponseEntity<?> getAllJournelEntryByUser(){
        Authentication authen = SecurityContextHolder.getContext().getAuthentication(); //getting username from securitycontext
        String  userName =authen.getName();
        User user= userService.findByUserName(userName);
        List<JournalEntry> all = user.getJournalEntriesList();
        if(all!= null && !all.isEmpty()){
        return new ResponseEntity<>(all,HttpStatus.OK);
      }
      return new  ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // controller will call services
    // controller keval endpoint banata hai and call service
    // business logic johai wo service me likhni hai
    //service  will  call repository


  //@PathVariable to get the datafrom url
  //@RequestBody is to get and extract data from request body


    // post request to that general url
    @PostMapping
    public  ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry myEntry){
      try{
          Authentication authen =SecurityContextHolder.getContext().getAuthentication();
          String userName = authen.getName();
        myEntry.setDate(LocalDateTime.now());
        journalEntryService.saveEntry(myEntry,userName);
        return new ResponseEntity<JournalEntry>(myEntry,HttpStatus.CREATED);
      }
      catch(Exception e){
        return new ResponseEntity<JournalEntry>(HttpStatus.BAD_REQUEST);
      }

    }


    // get data using specific id
    @GetMapping("/id/{myId}")
    public ResponseEntity<JournalEntry> getJournalEntryById(@PathVariable ObjectId myId) {
      // orElse is  used bcz the return type of findById in service is Optional which maybe or maybe not return anything
     // Optional<T> is a container object which may or may not hold a non-null value.

        Authentication authen =SecurityContextHolder.getContext().getAuthentication();
        String userName= authen.getName();
         User user= userService.findByUserName(userName);

       List<JournalEntry> collect=  user.getJournalEntriesList().stream().filter(x->x.getId().equals(myId)).collect(Collectors.toList());
         if(!collect.isEmpty()){
             Optional<JournalEntry> existEntry=journalEntryService.findById(myId);
         if(existEntry.isPresent()){       // isPresent is a method in Optional
           // existEntry.get() is kind of box optional so we need to get value from it
           return  new ResponseEntity<JournalEntry>(existEntry.get(), HttpStatus.OK);
         }}
      return  new ResponseEntity<JournalEntry>(HttpStatus.NOT_FOUND);
    }



    @DeleteMapping("/id/{myId}")
    public ResponseEntity<JournalEntry> deleteJournalEntryById(@PathVariable ObjectId  myId ) {
      try {
          Authentication auth =SecurityContextHolder.getContext().getAuthentication();
          String userName = auth.getName();
        Boolean removed= journalEntryService.deleteByID(myId,userName);
        if(removed) {
            return new ResponseEntity<JournalEntry>(HttpStatus.OK);
        }else{
            return new ResponseEntity<JournalEntry>(HttpStatus.NOT_FOUND);
        }
      }
      catch (Exception e){
        return new ResponseEntity<JournalEntry>(HttpStatus.BAD_REQUEST);
      }
    }



    @PutMapping("/id/{id}")
    public ResponseEntity<JournalEntry> updateJournalEntryById(@PathVariable ObjectId  id,@RequestBody JournalEntry newEntry) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userName = auth.getName();
        User user =userService.findByUserName(userName);
        List<JournalEntry> collect=  user.getJournalEntriesList().stream().filter(x->x.getId().equals(id)).collect(Collectors.toList());

       if(!collect.isEmpty()){
           Optional<JournalEntry> existEntry=journalEntryService.findById(id);
           if(existEntry.isPresent()){

           JournalEntry   oldEntry = existEntry.get();
         oldEntry.setTitle(newEntry.getTitle()!= null && !newEntry.getTitle().equals("") ? newEntry.getTitle() : oldEntry.getTitle() );
         oldEntry.setContent(newEntry.getContent()!= null && !newEntry.getContent().equals("")? newEntry.getContent() : oldEntry.getContent());
      journalEntryService.saveEntry(oldEntry);
         return new ResponseEntity<JournalEntry>(oldEntry,HttpStatus.OK);
       }}
      return new ResponseEntity<JournalEntry>(HttpStatus.NOT_FOUND);
    }

}
