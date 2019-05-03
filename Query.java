
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Query {

    Connection connection;

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
            ps = connection.prepareStatement("SELECT `TUAffliate` FROM `TU_TEST` WHERE `TUAffliate` = ?;");
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
        PreparedStatement ps;
        try {
            ps = connection.prepareStatement(" SELECT `ISBN, COPY, Title, AF, AM, AL," +
                                                        " \"Checked Out\" = 'no', \"On Hold\" = 'no'`" + 
                                                        " FROM `(BOOK JOIN REF_BOOK" +
                                                                " ON ISBN = REF_ISBN AND COPY = REF_COPY)" +
                                                                " JOIN AUTHOR" +
                                                                " ON ISBN = isbn AND COPY = copy`;");
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
            //select non-refernece books not on hold
            if (nothold && not_checked_out) {ps = connection.prepareStatement(" SELECT `ISBN, COPY, Title, AF, AM, AL, \"On Hold\" = 'no', \"Checked Out\" = 'no'`" + 
                                                                " FROM `(BOOK JOIN NREF_BOOK ON ISBN = NREF_ISBN AND COPY = NREF_COPY)" +
                                                                    " JOIN  AUTHOR ON ISBN = isbn AND COPY on copy`" + 
                                                                " WHERE `HOLD IS NULL AND CO_TUID IS NULL` ;");}
            //select non-refernece all books
            else if (nothold && !not_checked_out){ps = connection.prepareStatement(" SELECT `ISBN, COPY, Title, AF, AM, AL, \"On Hold\" = 'no', \"Checked Out\" = 'yes'`" +
                                                        " FROM `(BOOK JOIN NREF_BOOK ON ISBN = NREF_ISBN AND COPY = NREF_COPY)" +
                                                                " JOIN  AUTHOR ON ISBN = isbn AND COPY on copy`" +
                                                        " WHERE `HOLD IS NULL AND CO_TUID IS NOT NULL`;");}
            //select non-refernece books not on hold
            else if (!nothold && not_checked_out) {ps = connection.prepareStatement(" SELECT `ISBN, COPY, Title, AF, AM, AL, \"On Hold\" = 'yes', \"Checked Out\" = 'no'`" + 
                                                                " FROM `(BOOK JOIN NREF_BOOK ON ISBN = NREF_ISBN AND COPY = NREF_COPY)" +
                                                                    " JOIN  AUTHOR ON ISBN = isbn AND COPY on copy`" + 
                                                                " WHERE `HOLD IS NOT NULL AND CO_TUID IS NULL`;");}
            //select non-refernece all books
            else {ps = connection.prepareStatement(" SELECT `ISBN, COPY, Title, AF, AM, AL," +
                                                                    " \"Checked Out\" = (CASE" +
                                                                        " WHEN CO_TUID IS NOT NULL" +
                                                                        " THEN 'yes'" +
                                                                        " ELSE 'no' END)," +
                                                                    " \"On Hold\" = (CASE" +
                                                                        " WHEN HOLD IS NOT NULL" +
                                                                        " THEN 'yes'" +
                                                                        " ELSE 'no' END)`" +
                                                        " FROM `(BOOK JOIN NREF_BOOK ON ISBN = NREF_ISBN AND COPY = NREF_COPY)" +
                                                                " JOIN  AUTHOR ON ISBN = isbn AND COPY on copy`;");}
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
            //select books not on hold
            if (nothold && not_checked_out) {ps = connection.prepareStatement(" SELECT `ISBN, COPY, Title, AF, AM, AL, \"On Hold\" = 'no', \"Checked Out\" = 'no'`" + 
                                                                " FROM `((BOOK LEFT JOIN NREF_BOOK ON ISBN = NREF_ISBN AND COPY = NREF_COPY)" +
                                                                        " LEFT JOIN REF_BOOK ON ISBN = NREF_ISBN AND COPY = NREF_COPY)" +
                                                                            " JOIN  AUTHOR ON ISBN = isbn AND COPY = copy`" 
                                                                " WHERE `HOLD IS NULL AND CO_TUID IS NULL`;");}
            //select all books
            else if (nothold && !not_checked_out){ps = connection.prepareStatement(" SELECT `ISBN, COPY, Title, AF, AM, AL, \"On Hold\" = 'no', \"Checked Out\" = 'yes'`" +
                                                        " FROM `((BOOK LEFT JOIN NREF_BOOK ON ISBN = NREF_ISBN AND COPY = NREF_COPY)" +
                                                                        " LEFT JOIN REF_BOOK ON ISBN = NREF_ISBN AND COPY = NREF_COPY)" +
                                                                            " JOIN  AUTHOR ON ISBN = isbn AND COPY = copy`"
                                                        " WHERE `HOLD IS NULL AND CO_TUID IS NOT NULL`;");}
            //select books not on hold
            else if (!nothold && not_checked_out) {ps = connection.prepareStatement(" SELECT `ISBN, COPY, Title, AF, AM, AL, \"On Hold\" = 'yes', \"Checked Out\" = 'no'`" + 
                                                                " FROM `((BOOK LEFT JOIN NREF_BOOK ON ISBN = NREF_ISBN AND COPY = NREF_COPY)" +
                                                                        " LEFT JOIN REF_BOOK ON ISBN = NREF_ISBN AND COPY = NREF_COPY)" +
                                                                            " JOIN  AUTHOR ON ISBN = isbn AND COPY = copy`"
                                                                " WHERE `HOLD IS NOT NULL AND CO_TUID IS NULL`;");}
            //select all books
            else {ps = connection.prepareStatement(" SELECT `ISBN, COPY, Title, AF, AM, AL," +
                                                                    " \"Checked Out\" = (CASE" +
                                                                        " WHEN CO_TUID IS NOT NULL" +
                                                                        " THEN 'yes'" +
                                                                        " ELSE 'no' END)," +
                                                                    " \"On Hold\" = (CASE" +
                                                                        " WHEN HOLD IS NOT NULL" +
                                                                        " THEN 'yes'" +
                                                                        " ELSE 'no' END)`" +
                                                    " FROM `((BOOK LEFT JOIN NREF_BOOK ON ISBN = NREF_ISBN AND COPY = NREF_COPY)" +
                                                                    " LEFT JOIN REF_BOOK ON ISBN = NREF_ISBN AND COPY = NREF_COPY)" +
                                                                        " JOIN  AUTHOR ON ISBN = isbn AND COPY = copy`";);}
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
            ps = connection.prepareStatement("select * from BOOK Join AUTHOR ON ISBN = isbn AND COPY = copy  where  AF = ? AND AL = ?;");
            ps.setString(1, Fname);
            ps.setString(2, Lname);
            result = ps.executeQuery();
        } catch(Exception ex){
            ex.printStackTrace();
        }
        return result;
    }
}
