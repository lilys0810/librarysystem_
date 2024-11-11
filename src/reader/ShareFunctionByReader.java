package reader;
import pojo.Reader;
import util.DbOp;
import java.sql.ResultSet;

public class ShareFunctionByReader {

    public int findReaderCountById(int readerId) {
        String sql = "SELECT COUNT(*) readerCount FROM reader WHERE id = '" + readerId + "'";
        int count = 0;
        try {
            ResultSet resultSet = DbOp.executeQuery(sql);
            while (resultSet.next()) {
                count = resultSet.getInt("readerCount");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(count);
        return count;
    }

    public Reader findReaderById(int readerId) {
        Reader reader = new Reader();
        String selectSQL = "select * from reader where id = '" + readerId + "'";
        ResultSet resultSet = DbOp.executeQuery(selectSQL);
        try {
            while (resultSet.next()) {
                reader.setId(resultSet.getInt(1));
                reader.setReadername(resultSet.getString(2));
                reader.setReadtype(resultSet.getString(3));
                reader.setSex(resultSet.getString(4));
                reader.setMax_num(resultSet.getInt(5));
                reader.setDays_num(resultSet.getInt(6));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(selectSQL);
        System.out.println(reader.toString());
        return reader;
    }



}
