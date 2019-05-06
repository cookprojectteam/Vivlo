
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Query {

    Connection connection;
    static String current_tuid;

    public Query() { connection = DB.getConnection();
    }

    /**
     * Logs the user in and returns the resulting table value(s)
     * @param idNum ID Number of the TU affiliate
     */
    public ResultSet login(String idNum) {
        ResultSet result = null;
        PreparedStatement ps;
        try {
            ps = connection.prepareStatement("SELECT TUID FROM TU_AFFILIATE WHERE TUID = ?;");
            ps.setString(1, idNum);
            result = ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    /**
     * Sends library staff members to management page, and sends non library staff members to search page
     */
    public ResultSet loginFilter(String idNum) {
    	ResultSet result = null;
    	PreparedStatement ps;
    	try {
    		ps = connection.prepareStatement("SELECT LS_TUID FROM LIB_STAFF WHERE LS_TUID = ?;");
    		ps.setString(1, idNum);
    		result = ps.executeQuery();
    	}
    	catch (SQLException e) {
    		System.out.println("Something went wrong in loginFilter");
    		e.printStackTrace();
    	}
    	return result;
    }
    /**
     * Returns the query result for a book by ISBN number
     * @param ISBN The book's ISBN number
     */
    public ResultSet findBookByISBN(String ISBN) {
        ResultSet result = null;
        PreparedStatement ps = null;
        try{
            ps = connection.prepareStatement("select BOOK.ISBN, BOOK.COPY, BOOK.TITLE, AUTHOR.AF, AUTHOR.AM, AUTHOR.AL" + 
                                            " from BOOK Join AUTHOR ON BOOK.ISBN = AUTHOR.isbn AND BOOK.COPY = AUTHOR.copy where BOOK.ISBN = ?;");
            ps.setString(1, ISBN);
            result = ps.executeQuery();
        } catch(Exception ex){
            ex.printStackTrace();
        }
        return result;
    }

    /**
     * Finds the quiet floors in the library
     */
    public ResultSet findQuietFloors() {
        ResultSet result = null;
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement("select * from FLOOR where F_RULE=1");
            result = ps.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Finds the available computers
     */
    public ResultSet getAvailableComps() {
        ResultSet result = null;
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement("select count(*) from NON_BOOK_RESOURCE where R_TYPE='Computer'" +
                    " and R_DATE is null and R_TIME is null;");
            result = ps.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    //select refernece books
    public ResultSet selectReference() {
        ResultSet result = null;
        PreparedStatement ps;
        try {
            ps = connection.prepareStatement(" SELECT BOOK.ISBN, BOOK.COPY, BOOK.TITLE, AUTHOR.AF, AUTHOR.AM, AUTHOR.AL," +
                                                        " 'no' AS 'Checked Out', 'no' AS 'On Hold'" + 
                                                        " FROM (BOOK JOIN REF_BOOK" +
                                                                " ON BOOK.ISBN = REF_BOOK.REF_ISBN AND BOOK.COPY = REF_BOOK.REF_COPY)" +
                                                                " JOIN LEFT AUTHOR" +
                                                                " ON BOOK.ISBN = AUTHOR.isbn AND BOOK.COPY = AUTHOR.copy;");
            result = ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public ResultSet selectNon_Reference(boolean nothold, boolean not_checked_out) {
        ResultSet result = null;
        PreparedStatement ps;
        try {
            //select non-refernece books not on hold and not checked out
            if (nothold && not_checked_out) {ps = connection.prepareStatement(" SELECT BOOK.ISBN, BOOK.COPY, BOOK.TITLE, AUTHOR.AF, AUTHOR.AM, AUTHOR.AL, 'no' AS 'On Hold', 'no' AS 'Checked Out'" + 
                                                                " FROM (BOOK JOIN NREF_BOOK ON BOOK.ISBN = NREF_BOOK.NREF_ISBN AND BOOK.COPY = NREF_BOOK.NREF_COPY)" +
                                                                    " JOIN LEFT AUTHOR ON BOOK.ISBN = AUTHOR.isbn AND BOOK.COPY on AUTHOR.copy" + 
                                                                " WHERE NREF_BOOK.HOLD IS NULL AND NREF_BOOK.CO_TUID IS NULL ;");}
            //select non-refernece all books not on hold
            else if (nothold && !not_checked_out){ps = connection.prepareStatement(" SELECT BOOK.ISBN, BOOK.COPY, BOOK.TITLE, AUTHOR.AF, AUTHOR.AM, AUTHOR.AL, 'no' AS 'On Hold', 'yes' AS 'Checked Out'" +
                                                        " FROM (BOOK JOIN NREF_BOOK ON BOOK.ISBN = NREF_BOOK.NREF_ISBN AND BOOK.COPY = NREF_BOOK.NREF_COPY)" +
                                                                " JOIN LEFT AUTHOR ON BOOK.ISBN = AUTHOR.isbn AND BOOK.COPY on AUTHOR.copy" +
                                                        " WHERE NREF_BOOK.HOLD IS NULL AND NREF_BOOK.CO_TUID IS NOT NULL;");}
            //select non-refernece books not checked out
            else if (!nothold && not_checked_out) {ps = connection.prepareStatement(" SELECT BOOK.ISBN, BOOK.COPY, BOOK.TITLE, AUTHOR.AF, AUTHOR.AM, AUTHOR.AL, 'yes' AS 'On Hold', 'no' As 'Checked Out'" + 
                                                                " FROM (BOOK JOIN NREF_BOOK ON BOOK.ISBN = NREF_BOOK.NREF_ISBN AND BOOK.COPY = NREF_BOOK.NREF_COPY)" +
                                                                    " LEFT JOIN AUTHOR ON BOOK.ISBN = AUTHOR.isbn AND BOOK.COPY on AUTHOR.copy" + 
                                                                " WHERE NREF_BOOK.HOLD IS NOT NULL AND NREF_BOOK.CO_TUID IS NULL;");}
            //select non-refernece all books
            else {ps = connection.prepareStatement(" SELECT AUTHOR.ISBN, AUTHOR.COPY, AUTHOR.TITLE, AUTHOR.AF, AUTHOR.AM, AUTHOR.AL," +
                                                                    " CASE" +
                                                                        " WHEN NREF_BOOK.CO_TUID IS NOT NULL" +
                                                                        " THEN 'yes'" +
                                                                        " ELSE 'no' END AS 'Checked Out'," +
                                                                    " CASE" +
                                                                        " WHEN NREF_BOOK.HOLD IS NOT NULL" +
                                                                        " THEN 'yes'" +
                                                                        " ELSE 'no' END AS 'On Hold'" +
                                                        " FROM (BOOK JOIN NREF_BOOK ON BOOK.ISBN = NREF_BOOK.NREF_ISBN AND BOOK.COPY = NREF_BOOK.NREF_COPY)" +
                                                                " LEFT JOIN AUTHOR ON BOOK.ISBN = AUTHOR.isbn AND BOOK.COPY on AUTHOR.copy;");}
            result = ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    //select all books
    public ResultSet selectBooks(boolean nothold, boolean not_checked_out) {
        ResultSet result = null;
        PreparedStatement ps;
        try {
            //select books not on hold and checked out 
            if (nothold && not_checked_out) {ps = connection.prepareStatement(" SELECT BOOK.ISBN, BOOK.COPY, BOOK.TITLE, AUTHOR.AF, AUTHOR.AM, AUTHOR.AL, 'no' AS 'On Hold', 'no' AS 'Checked Out'" + 
                                                                " FROM ((BOOK LEFT JOIN NREF_BOOK ON BOOK.ISBN = NREF_BOOK.NREF_ISBN AND BOOK.COPY = NREF_BOOK.NREF_COPY)" +
                                                                        " LEFT JOIN REF_BOOK ON BOOK.ISBN = REF_BOOK.REF_ISBN AND BOOK.COPY = REF_BOOK.REF_COPY)" +
                                                                            " LEFT JOIN AUTHOR ON BOOK.ISBN = AUTHOR.isbn AND BOOK.COPY = AUTHOR.copy" +
                                                                " WHERE NREF_BOOK.HOLD IS NULL AND NREF_BOOK.CO_TUID IS NULL;");}
            //select all books not on hold
            else if (nothold && !not_checked_out){ps = connection.prepareStatement(" SELECT BOOK.ISBN, BOOK.COPY, BOOK.TITLE, AUTHOR.AF, AUTHOR.AM, AUTHOR.AL, 'no' AS 'On Hold', 'yes' AS 'Checked Out'" +
                                                        " FROM ((BOOK LEFT JOIN NREF_BOOK ON BOOK.ISBN = NREF_BOOK.NREF_ISBN AND BOOK.COPY = NREF_BOOK.NREF_COPY)" +
                                                                        " LEFT JOIN REF_BOOK ON BOOK.ISBN = REF_BOOK.REF_ISBN AND BOOK.COPY = REF_BOOK.REF_COPY)" +
                                                                            " LEFT JOIN AUTHOR ON BOOK.ISBN = AUTHOR.isbn AND BOOK.COPY = AUTHOR.copy" +
                                                        " WHERE NREF_BOOK.HOLD IS NULL AND NREF_BOOK.CO_TUID IS NOT NULL;");}
            //select books not checked out
            else if (!nothold && not_checked_out) {ps = connection.prepareStatement(" SELECT BOOK.ISBN, BOOK.COPY, BOOK.TITLE, AUTHOR.AF, AUTHOR.AM, AUTHOR.AL, 'yes' AS 'On Hold', 'no' AS 'Checked Out'" + 
                                                                " FROM ((BOOK LEFT JOIN NREF_BOOK ON BOOK.ISBN = NREF_BOOK.NREF_ISBN AND BOOK.COPY = NREF_BOOK.NREF_COPY)" +
                                                                        " LEFT JOIN REF_BOOK ON BOOK.ISBN = REF_BOOK.REF_ISBN AND BOOK.COPY = REF_BOOK.REF_COPY)" +
                                                                            " LEFT JOIN AUTHOR ON BOOK.ISBN = AUTHOR.isbn AND BOOK.COPY = AUTHOR.copy" +
                                                                " WHERE NREF_BOOK.HOLD IS NOT NULL AND NREF_BOOK.CO_TUID IS NULL;");}
            //select all books
            else {ps = connection.prepareStatement(" SELECT BOOK.ISBN, BOOK.COPY, BOOK.TITLE, AUTHOR.AF, AUTHOR.AM, AUTHOR.AL," +
                                                                    " CASE" +
                                                                        " WHEN NREF_BOOK.CO_TUID IS NOT NULL" +
                                                                        " THEN 'yes'" +
                                                                        " ELSE 'no' END AS 'Checkeed Out'," +
                                                                    " CASE" +
                                                                        " WHEN NREF_BOOK.HOLD IS NOT NULL" +
                                                                        " THEN 'yes'" +
                                                                        " ELSE 'no' END AS 'On Hold'" +
                                                    " FROM ((BOOK LEFT JOIN NREF_BOOK ON BOOK.ISBN = NREF_BOOK.NREF_ISBN AND BOOK.COPY = NREF_BOOK.NREF_COPY)" +
                                                                    " LEFT JOIN REF_BOOK ON BOOK.ISBN = REF_BOOK.REF_ISBN AND BOOK.COPY = REF_BOOK.REF_COPY)" +
                                                                        " LEFT JOIN AUTHOR ON BOOK.ISBN = AUTHOR.isbn AND BOOK.COPY = AUTHOR.copy;");}
            result = ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    
    //generate a list of books by author
    public ResultSet listByAuthor(String Fname, String Lname) {
        ResultSet result = null;
        PreparedStatement ps = null;
        try{
            ps = connection.prepareStatement("select BOOK.ISBN, BOOK.COPY, BOOK.TITLE, AUTHOR.AF, AUTHOR.AM, AUTHOR.AL" + 
            " from BOOK Join AUTHOR ON BOOK.ISBN = AUTHOR.isbn AND BOOK.COPY = AUTHOR.copy" +
            " where  AUTHOR.AF = ? AND AUTHOR.AL = ?;");
            ps.setString(1, Fname);
            ps.setString(2, Lname);
            result = ps.executeQuery();
        } catch(Exception ex){
            ex.printStackTrace();
        }
        return result;
    }
    
    //search books by title
    //FUNCTIONAL
    public ResultSet findBookByTitle(String Title) {
        ResultSet result = null;
        PreparedStatement ps = null;
        try{
            ps = connection.prepareStatement("SELECT BOOK.ISBN, BOOK.COPY, BOOK.TITLE, AUTHOR.AF, AUTHOR.AM, AUTHOR.AL" +
            " from BOOK Join AUTHOR ON BOOK.ISBN = AUTHOR.isbn AND BOOK.COPY = AUTHOR.copy" + " where BOOK.TITLE = ?;");
            ps.setString(1, Title);
            result = ps.executeQuery();
        } catch(Exception ex){
            ex.printStackTrace();
        }
        return result;
    }
    
    //count books on hold
    //functional
    public ResultSet countBooksOnHold(){
        ResultSet result = null;
        PreparedStatement ps = null;
        try{
            ps = connection.prepareStatement("select COUNT(HOLD) from NREF_BOOK where HOLD IS NOT NULL;");
            result = ps.executeQuery();
        } catch(Exception ex){
            ex.printStackTrace();
        }
        return result;
        
    }
    //count student workers
    public ResultSet countStudentWorkers() {
    	ResultSet result = null;
    	PreparedStatement ps = null;
    	try {
    		ps = connection.prepareStatement("SELECT COUNT(*) FROM STUDENT_WORKER;");
    		result = ps.executeQuery();
    	}
    	catch (SQLException e) {
    		System.out.println("Something went wrong in countStudentWorkers query");
    		e.printStackTrace();
       	}
    	return result;
    	
    }
    
    //Produce list of departments
    public ResultSet listOfDepartments(){
        ResultSet result = null;
        PreparedStatement ps = null;
        try{
            ps = connection.prepareStatement("select * from DEPART;");
            result = ps.executeQuery();
        } catch(Exception ex){
            ex.printStackTrace();
        }
        return result;
    }
    
    //Descending list of books by date
    public ResultSet booksPublishingDate(){
        ResultSet result = null;
        PreparedStatement ps = null;
        try{
            ps = connection.prepareStatement("Select BOOK.ISBN, BOOK.COPY, BOOK.TITLE, AUTHOR.AF, AUTHOR.AM, AUTHOR.AL , BOOK.PDATE" + 
            " from BOOK Join AUTHOR ON BOOK.ISBN = AUTHOR.isbn AND BOOK.COPY = AUTHOR.copy OREDER BY BOOK.PDATE DESC;");
            result = ps.executeQuery();
        } catch(Exception ex){
            ex.printStackTrace();
        }
        return result;
    }
    
    //checkout book
    public void checkout(String ISBN, String COPY){        
        PreparedStatement ps = null;
        try{
            ps = connection.prepareStatement("UPDATE NREF_BOOK SET CI_DATE = NULL, CI_TUID = NULL, CO_DATE = (SELECT CURDATE()), CO_TUID = ? WHERE NREF_ISBN = ? AND NREF_COPY = ? ;");            
            ps.setString(1, current_tuid);
            ps.setString(2, ISBN);
            ps.setString(3, COPY);
            ps.executeUpdate();
        } catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    //check in book
    public void checkin(String ISBN, String COPY){        
        PreparedStatement ps = null;
        try{
            ps = connection.prepareStatement("UPDATE NREF_BOOK SET CO_DATE = NULL , CO_TUID = NULL , CI_TUID = ? , CI_DATE = (SELECT CURDATE()) WHERE NREF_ISBN = ? AND NREF_COPY = ? ;");            
            ps.setString(1, current_tuid);
            ps.setString(2, ISBN);
            ps.setString(3, COPY);
            ps.executeUpdate();
        } catch(Exception ex){
            ex.printStackTrace();
        }       
    }
    
    //request a book
     public void requestBook(String ISBN, String COPY){
        PreparedStatement ps = null;
        try{
            ps = connection.prepareStatement("INSERT INTO REQUEST VALUES ? , ? , ?");
            ps.setString(1, current_tuid);
            ps.setString(2, ISBN);
            ps.setString(3, COPY);
            ps.executeQuery();
        } catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    //view book request
     public ResultSet viewRequest(){
        ResultSet result = null;
        PreparedStatement ps = null;
        try{
            ps = connection.prepareStatement("SELECT * FROM REQUEST;");
            result = ps.executeQuery();
        } catch(Exception ex){
            ex.printStackTrace();
        }
        return result;
    }
     public ResultSet studyRoomAvailable() {
    	 ResultSet result = null;
    	 PreparedStatement ps = null;
    	 try {
    		 ps=connection.prepareStatement("SELECT R_NUM FROM NON_BOOK_RESOURCE WHERE R_TYPE LIKE 'Study Room' AND R_DATE IS NULL AND R_TIME IS NULL;");
    		 result=ps.executeQuery();
    	 }
    	 catch (SQLException e) {
    		 e.printStackTrace();
    	 }
    	 return result;
     }
     
     public ResultSet show(String isbn,String af,String al,String title) { 
    	 ResultSet result = null;
    	 PreparedStatement ps = null;
    	 try { 
    		 ps = connection.prepareStatement("SELECT DISTINCT JOEY.ISBN,JOEY.COPY,JOEY.AF,JOEY.AL,JOEY.TITLE,JOEY.SIZE FROM ( (SELECT BOOK.ISBN,BOOK.COPY,BOOK.TITLE,BOOK.SIZE,AUTHOR.AF,AUTHOR.AL FROM BOOK,AUTHOR WHERE BOOK.ISBN = AUTHOR.isbn) AS JOEY) WHERE JOEY.ISBN LIKE ? OR JOEY.AF LIKE ? OR JOEY.AL LIKE ? OR JOEY.TITLE LIKE ? ;");
    		 ps.setString(1,isbn);
    		 ps.setString(2, af);
    		 ps.setString(3, al);
    		 ps.setString(4, title);
    		 result = ps.executeQuery();
    	 } catch(Exception ex) {
    		 System.out.println("Failed here");
    		 ex.printStackTrace();
    	 }
    	 return result;
     }
     public ResultSet listRef() {
    	 ResultSet result = null;
		 PreparedStatement ps = null;
    	 try {    		 
    		 ps = connection.prepareStatement("SELECT ISBN, COPY, TITLE, GENRE, PUBLISHER, SIZE FROM BOOK, REF_BOOK WHERE ISBN=REF_ISBN AND COPY=REF_COPY;");
    		 result = ps.executeQuery();
    		 
    	 }
    	 catch (SQLException e) {
    		 e.printStackTrace();
    	 }
    	 return result;
     }
     public ResultSet listNref() {
    	 ResultSet result = null;
		 PreparedStatement ps = null;
    	 try {    		 
    		 ps = connection.prepareStatement("SELECT ISBN, COPY, TITLE, GENRE, PUBLISHER, SIZE FROM NREF_BOOK, BOOK WHERE NREF_ISBN=ISBN AND NREF_COPY=COPY AND CO_TUID IS NULL;");
    		 result=ps.executeQuery();
    	 }
    	 catch (SQLException e) {
    		 e.printStackTrace();
    	 }
    	 return result;
     }
     public ResultSet isLibStaff() {
    	 ResultSet result = null;
    	 PreparedStatement ps = null;
    	 try {
    		 ps = connection.prepareStatement("SELECT * FROM LIB_STAFF WHERE LS_TUID = ?;");
    		 ps.setString(1, current_tuid);
    		 result=ps.executeQuery();
    		 
    	 }
    	 catch (SQLException e) {
    		 e.printStackTrace();
    		 
    	 }
    	 return result;
     }
     public ResultSet departInformation() {
    	 ResultSet result = null;
    	 PreparedStatement ps = null;
    	 try {
    		 ps = connection.prepareStatement("SELECT COUNT(*) AS EMPLOYEES, D_NUM, D_NAME, D_HEAD FROM DEPART,LIB_STAFF WHERE LS_DEPART=D_NUM GROUP BY D_NUM;");
    		 result = ps.executeQuery();
    		 
    	 }
    	 catch (SQLException e) {
    		 e.printStackTrace();
    	 }
    	 return result;
     }
     public ResultSet FloorInformation() {
    	 ResultSet result = null;
    	 PreparedStatement ps = null;
    	 try {
    		 ps = connection.prepareStatement("SELECT F_NUM, F_RULE, COUNT(*) AS RESOURCES FROM FLOOR, NON_BOOK_RESOURCE WHERE F_NUM=R_FLOOR GROUP BY F_NUM ;");
    		 result = ps.executeQuery();
    		 
    	 }
    	 catch (SQLException e) {
    		 e.printStackTrace();
    	 }
    	 return result;
     }
     public ResultSet StaffInformation() {
    	 ResultSet result = null;
    	 PreparedStatement ps = null;
    	 try {
    		 ps = connection.prepareStatement("SELECT AFF, AFL, AFPhone, TUID, LS_FLOOR, D_NAME FROM LIB_STAFF,TU_AFFILIATE,DEPART WHERE LS_TUID=TUID AND LS_DEPART=D_NUM;");
    		 result = ps.executeQuery();
    		 
    	 }
    	 catch (SQLException e) {
    		 e.printStackTrace();
    	 }
    	 return result;
     }
     public ResultSet overDueBooks() {
    	 ResultSet result = null;
    	 PreparedStatement ps = null;
    	 try {
    		 ps = connection.prepareStatement("SELECT NREF_ISBN, NREF_COPY, CO_TUID FROM NREF_BOOK WHERE CO_DATE + 20 < CURDATE();");
    		 result = ps.executeQuery();
    		 
    	 }
    	 catch (SQLException e) {
    		 e.printStackTrace();
    	 }
    	 return result;
     }
     public ResultSet booksHold() {
    	 ResultSet result = null;
    	 PreparedStatement ps = null;
    	 try {
    		 ps = connection.prepareStatement("SELECT NREF_ISBN, NREF_COPY, HOLD FROM NREF_BOOK WHERE HOLD IS NOT NULL;");
    		 result = ps.executeQuery();
    		 
    	 }
    	 catch (SQLException e) {
    		 e.printStackTrace();
    	 }
    	 return result;
     }
     public ResultSet booksCheckedOut() {
    	 ResultSet result = null;
    	 PreparedStatement ps = null;
    	 try {
    		 ps = connection.prepareStatement("SELECT NREF_ISBN, NREF_COPY, CO_TUID FROM NREF_BOOK WHERE CO_TUID IS NOT NULL;");
    		 result = ps.executeQuery();
    		 
    	 }
    	 catch (SQLException e) {
    		 e.printStackTrace();
    	 }
    	 return result;
     
     }
     public void newBook(String ISBN, int COPY, String TITLE, String GENRE, int PDATE, String PUBLISHER, int SIZE,String af,String am, String al) { 
    	 
    	 PreparedStatement ps = null;
    	 PreparedStatement ps1 = null;
    	 try { 
    		 ps = connection.prepareStatement("INSERT INTO BOOK VALUES (?,?,?,?,DATE(?),?,?);");
    		 ps1 = connection.prepareStatement("INSERT INTO AUTHOR VALUES (?,?,?,?,?);");
    		  ps.setString(1, ISBN);
              ps.setInt(2, COPY);
              ps.setString(3, TITLE);
              ps.setString(4, GENRE);
              ps.setInt(5, PDATE);
              ps.setString(6, PUBLISHER);
              ps.setInt(7,SIZE);
              
              ps1.setString(1, ISBN);
              ps1.setInt(2, COPY);
              ps1.setString(3, af);
              ps1.setString(4, am);
              ps1.setString(5,  al);
    		 ps.executeUpdate();
    		 ps1.executeUpdate();
    	 } catch(Exception ex) {
    		 ex.printStackTrace();
    	 }
    	 
     }
}
