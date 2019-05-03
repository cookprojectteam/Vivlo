
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
	public selectReference() {
		try {
            result = connection.prepareStatement("SELECT `ISBN, COPY, AF, AM, AL, "no", "no"` 
														FROM `(BOOK JOIN REF_BOOK
																ON ISBN = REF_ISBN AND COPY = REF_COPY
																) JOIN AUTHOR
																ON ISBN = isbn AND COPY = copy`
														");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
	}
	public selectNon_Reference(nothold) {
		try {
			if (nothold) {result = connection.prepareStatement("SELECT `ISBN, COPY, AF, AM, AL,
																	(CASE
																		WHEN CO_TUID IS NOT NULL
																		THEN "yes"
																		ELSE "no"), "no"`
																FROM `BOOK JOIN NREF_BOOK ON ISBN = NREF_ISBN AND COPY = NREF_COPY `
																WHERE `HOLD IS NULL`");
						}
            else {result = connection.prepareStatement("SELECT `ISBN, COPY, AF, AM, AL,
																	(CASE
																		WHEN CO_TUID IS NOT NULL
																		THEN "yes"
																		ELSE "no"),
																	(CASE
																		WHEN HOLD IS NOT NULL
																		THEN "yes"
																		ELSE "no"` 
														FROM `BOOK JOIN NREF_BOOK ON ISBN = NREF_ISBN AND COPY = NREF_COPY`")}
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
	}
	public selectBooks(hold)try {
			if (nothold) {result = connection.prepareStatement("SELECT `ISBN, COPY, AF, AM, AL,
																	(CASE
																		WHEN CO_TUID IS NOT NULL
																		THEN "yes"
																		ELSE "no"), "no"` 
																FROM `BOOK LEFT JOIN NREF_BOOK ON ISBN = NREF_ISBN AND COPY = NREF_COPY` 
																WHERE `HOLD IS NULL`");}
            else {result = connection.prepareStatement("SELECT `ISBN, COPY, AF, AM, AL,
																	(CASE
																		WHEN CO_TUID IS NOT NULL
																		THEN "yes"
																		ELSE "no"),
																	(CASE
																		WHEN HOLD IS NOT NULL
																		THEN "yes"
																		ELSE "no"`
														FROM `BOOK LEFT JOIN NREF_BOOK ON ISBN = NREF_ISBN AND COPY = NREF_COPY`")}
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
	}
}
