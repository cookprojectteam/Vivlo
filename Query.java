
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Query {

    Connection connection;

    public Query() {
        connection = DB.getConnection();
    }

    /**
     * Logs the user in and returns the resulting table value(s)
     * @param idNum ID Number of the TU affiliate
     */
    public ResultSet login(String idNum) {
        ResultSet result = null;
        PreparedStatement ps;
        try {
            ps = connection.prepareStatement("SELECT `TUAffliate` FROM `TU_TEST` WHERE `TUAffliate` = ?");
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
    public ResultSet findBook(String ISBN) {
        ResultSet result = null;
        PreparedStatement ps = null;
        try{
            ps = connection.prepareStatement("select * from users where isbn = ?");
            ps.setString(1, ISBN);
            result = ps.executeQuery();
        } catch(Exception ex){
            ex.printStackTrace();
        }
        return result;
    }

    //select refernece books
    public ResultSet selectReference() {

        ResultSet result = null;
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement("SELECT ISBN, COPY, AF, AM, AL Checked Out = no, On Hold = no FROM (BOOK JOIN REF_BOOK ON ISBN = REF_ISBN AND COPY = REF_COPY) JOIN AUTHOR ON ISBN = isbn AND COPY = copy");
            result = ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    
}
