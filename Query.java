
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
	public selectReference(hold) {
		try {
			if (hold) {result = connection.prepareStatement("SELECT `*` FROM `BOOK JOIN REF_BOOK ON ISBN = REF_ISBN AND COPY = REF_COPY` WHERE `HOLD IS NOT NULL`");}
            else {result = connection.prepareStatement("SELECT `*` FROM `BOOK JOIN REF_BOOK ON ISBN = REF_ISBN AND COPY = REF_COPY`")}
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
	}
	public selectNon_Reference(hold) {
		try {
			if (hold) {result = connection.prepareStatement("SELECT `*` FROM `BOOK JOIN NREF_BOOK ON ISBN = NREF_ISBN AND COPY = NREF_COPY ` WHERE `HOLD IS NOT NULL`");}
            else {result = connection.prepareStatement("SELECT `*` FROM `BOOK JOIN NREF_BOOK ON ISBN = NREF_ISBN AND COPY = NREF_COPY`")}
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
	}
	public selectBooks(hold)try {
			if (hold) {result = connection.prepareStatement("SELECT `*` FROM `BOOK LEFT JOIN NREF_BOOK ON ISBN = NREF_ISBN AND COPY = NREF_COPY` WHERE `HOLD IS NOT NULL`");}
            else {result = connection.prepareStatement("SELECT `*` FROM `BOOK LEFT JOIN NREF_BOOK ON ISBN = NREF_ISBN AND COPY = NREF_COPY` WHERE `HOLD IS NOT NULL`")}
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
	}
}
