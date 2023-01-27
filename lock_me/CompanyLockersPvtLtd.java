package project;
import java.io.File;
import java.util.Scanner;

public class CompanyLockersPvtLtd {
	public String fname;	
	
	
	void showFiles() throws Exception {      //Method 1
		File f = new File("E://ProFile//");
	    String a[] = f.list();
	    for(String fn : a) 
	    	System.out.println(fn);
	    System.out.println();
	    this.mainMenue();
	}
	
	void addFile(String fname) throws Exception {  //Method 2
		this.fname = fname;
		File f = new File("E://ProFile//"+fname);
		
		if(f.exists()==true) {
			System.out.println("File already Exists");
		}
		else {
			f.createNewFile();
		    System.out.println("File has been Created");	
		}
		System.out.println();
		this.subMenue();
	}
	
	void deleteFile(String fname) throws Exception {  //Method 3
		this.fname = fname;
		File f = new File("E://ProFile//"+fname);
		
		if(f.exists()==true) {
			f.delete();
			System.out.println("File has been Deleted");
		}
		else {
		    System.out.println("File not Found");	
		}
		System.out.println();
		this.subMenue();
	}
	
	void searchFile(String fname) throws Exception{   //Method 4
		this.fname = fname;
		File f = new File("E://ProFile//"+fname);
		
		if(f.exists()==true) 
			System.out.println("File Found");
		else 
		    System.out.println("File Not Found");
		this.subMenue();
	}

	void subMenue() throws Exception{  //Method 5		
		System.out.println("Press 1 To Add File");
    	System.out.println("Press 2 To Delete File");
    	System.out.println("Press 3 To Search File");
    	System.out.println("Press 4 To Go Back To the Main Menue");
    	
    	System.out.println();
    	
    	Scanner sc = new Scanner(System.in);
    	System.out.println("Enter Your Choice");
    	int cho = sc.nextInt();
    	switch(cho) {
        	case 1:
        		System.out.println("Enter the file name: ");
        		String fname1 = sc.next();
        		this.addFile(fname1);
        		break;
        	
        	case 2:
        		System.out.println("Enter the file name: ");
        		String fname2 = sc.next();
        		this.deleteFile(fname2);
        		break;
        	
        	case 3:
        		System.out.println("Enter the file name: ");
        		String fname3 = sc.next();
        		this.searchFile(fname3);
        		break;
        	
        	case 4:
        		System.out.println();
        		this.mainMenue();
        		break;
        	default:
            	System.out.println("Invalid Choice");
                break;
        } 
	}
	
	void mainMenue() throws Exception{    //Method 6
		System.out.println("Press 1 to Show all files"); 
        System.out.println("Press 2 for Basic Operations");
        System.out.println("Press 3 to Exit From App");
        System.out.println();
        System.out.println("Enter Your Choice: ");
        
        Scanner sc = new Scanner(System.in);
        int mch = sc.nextInt();
        switch(mch) {
        
        case 1:
        	System.out.println("These are the Files Available");
        	this.showFiles();
            break;
        
        case 2:
        	this.subMenue();
        	break;
        	
        case 3:
        	System.out.println("Exiting...");
        	System.exit(0);
        	sc.close();
            break;
        
        default:
        	System.out.println("Invalid Choice");
            break;    
        }
	}
	
	public static void main(String[] args) throws Exception {
		
		System.out.println("===============================");
        System.out.println("Welcome To \"LOCK ME\" App");
        System.out.println();
        System.out.println("Presented by TEJASWINI REDDY");
        System.out.println("===============================");
        
        System.out.println();
        
        CompanyLockersPvtLtd c = new CompanyLockersPvtLtd(); //Object to call the functions.
        
        try {
        	c.mainMenue();
        }
        catch(Exception e){
        	System.out.println("Wrong Entry");
        }
        
	}
}
