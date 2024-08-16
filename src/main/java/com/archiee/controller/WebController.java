package com.archiee.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.archiee.model.MList;
import com.archiee.model.scheme;
import com.archiee.repo.MListRepository;
import com.archiee.repo.passbookRepository;
import com.archiee.repo.schemeRepository;


@RestController
@RequestMapping("/modules")
//@CrossOrigin(origins = "http://localhost:3000")
public class WebController {
	

	@Autowired
	MListRepository repository1;

	@Autowired
	passbookRepository repository2;

	@Autowired
	schemeRepository repository3;

	@RequestMapping("/test")  
    public String index(){  
        return"controller logic executed successfully...!!";  
    }  
	

	@RequestMapping(value="/newAcc",method = RequestMethod.POST)
	public String insertCustomer(@RequestBody MList mList){
		boolean status = repository1.save(mList) != null;	
		long id=mList.getAccno();	
		return status? "Successfully added the customer!!! Your Account no. is"+id : "Cant add the customer";
	}
	
	@PostMapping("/linking/{accno}/{aadhar}")
	public String aadharupdate(@PathVariable Long accno, @PathVariable long aadhar) {
		Optional<MList> result= repository1.findByaccno(accno);
		if (result.isPresent()) {
            MList mList = result.get();
            mList.setAadharCard(aadhar);
            boolean status = repository1.save(mList)!= null;
			return status? "Successfully updated the Aadhar Card Details!!! Your Account no. is "+accno : "Cant update aadhar card";
        } else {
            // Handle the case where the account is not found
            throw new RuntimeException("Account not found for accno: " + accno);
        }
    }

	@PostMapping("/linking/{accno}/{pancard}")
	public String pancardupdate(@PathVariable Long accno, @PathVariable long pancard) {
		
		Optional<MList> result= repository1.findByaccno(accno);
		if (result.isPresent()) {
            MList mList = result.get();
            mList.setPanCard(pancard);
            boolean status = repository1.save(mList)!= null;
			return status? "Successfully updated the Pan Card Details!!! Your Account no. is "+accno : "Cant update Pan card";
        } else {
        
            throw new RuntimeException("Account not found for accno: " + accno);
        }
    }

	@PostMapping("/Acc_Upd/Fname/{accno}/{firstname}")
	public String FirstNameupdate(@PathVariable Long accno, @PathVariable String firstname) {
		
		Optional<MList> result= repository1.findByaccno(accno);
		if (result.isPresent()) {
            MList mList = result.get();
            mList.setFirstName(firstname);
            boolean status = repository1.save(mList)!= null;
			return status? "Successfully updated the First Name!!! Your Account no. is "+accno : "Cant update Pan card";
        } else {
        
            throw new RuntimeException("Account not found for accno: " + accno);
        }
    }

	@PostMapping("/Acc_Upd/Lname/{accno}/{lastname}")
	public String LastNameupdate(@PathVariable Long accno, @PathVariable String lastname) {
		
		Optional<MList> result= repository1.findByaccno(accno);
		if (result.isPresent()) {
            MList mList = result.get();
            mList.setLastName(lastname);
            boolean status = repository1.save(mList)!= null;
			return status? "Successfully updated the Last Name!!! Your Account no. is "+accno : "Cant update Pan card";
        } else {
        
            throw new RuntimeException("Account not found for accno: " + accno);
        }
    }

	@PostMapping("/Acc_Upd/Contact_no/{accno}/{contactno}")
	public String LastNameupdate(@PathVariable Long accno, @PathVariable Long contactno) {
		
		Optional<MList> result= repository1.findByaccno(accno);
		if (result.isPresent()) {
            MList mList = result.get();
            mList.setContactNo(contactno);
            boolean status = repository1.save(mList)!= null;
			return status? "Successfully updated the Last Name!!! Your Account no. is "+accno : "Cant update Pan card";
        } else {
        
            throw new RuntimeException("Account not found for accno: " + accno);
        }
    }
	
	
	
	@RequestMapping("/findallschemes")
	public List<scheme> findAll(){
		
		return (List<scheme>) repository3.findAll();
	}
	
	@RequestMapping("/findbyidschemes/{id}")
	public Optional<scheme> findById(@PathVariable long id){
		
		return repository3.findBysno(id);
	}

	@RequestMapping("/passbook/accno")
	public Optional<com.archiee.model.passbook> passbook(@RequestParam("accno") long accno){
		Optional<com.archiee.model.passbook> result = repository2.findByaccno(accno);
		return result;
	}

	@PostMapping("/passbook/deposit")
	public HttpStatus deposit(@RequestBody com.archiee.model.passbook passUpdate){
		boolean status = repository2.save(passUpdate) != null;		
		return status? HttpStatus.CREATED : HttpStatus.BAD_REQUEST;
	}

	@PostMapping("/passbook/withdrawl")
	public HttpStatus withdrawl(@RequestBody com.archiee.model.passbook passUpdate){
		boolean status = repository2.save(passUpdate) != null;		
		return status? HttpStatus.CREATED : HttpStatus.BAD_REQUEST;
	}
	
}
