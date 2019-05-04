
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
    public ResultSet checkout(String ISBN, String COPY){
        ResultSet result = null;
        PreparedStatement ps = null;
        try{
            ps = connection.prepareStatement("UPDATE NREF_BOOK SET CO_DATE = GETDATE(), CO_TUID = ? WHERE NREF_ISBN = ? AND NREF_COPY = ? ;");
            result = ps.executeQuery();
            ps.setString(1, current_tuid);
            ps.setString(2, ISBN);
            ps.setString(3, COPY);
        } catch(Exception ex){
            ex.printStackTrace();
        }
        return result;
    }
    
    //checkin book
    public ResultSet checkin(String ISBN, String COPY){
        ResultSet result = null;
        PreparedStatement ps = null;
        try{
            ps = connection.prepareStatement("UPDATE NREF_BOOK SET CO_DATE = NULL , CO_TUID = NULL , CI_TUID = ? , CI_DATE = GETDATE() WHERE NREF_ISBN = ? AND NREF_COPY = ? ;");
            result = ps.executeQuery();
            ps.setString(1, current_tuid);
            ps.setString(2, ISBN);
            ps.setString(3, COPY);
        } catch(Exception ex){
            ex.printStackTrace();
        }
        return result;
    }
    
    //View over due books
     public ResultSet viewOverDue(){
        ResultSet result = null;
        PreparedStatement ps = null;
        try{
            ps = connection.prepareStatement("SELECT NREF_ISBN, NREF_COPY, dateadd(dd,20,getdate()) AS 'was due', CO_TUID AS 'Checked Out to' From NREF_BOOK WHERE CO_DATE > dateadd(dd,20,getdate()) ;");
            result = ps.executeQuery();
        } catch(Exception ex){
            ex.printStackTrace();
        }
        return result;
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
}
