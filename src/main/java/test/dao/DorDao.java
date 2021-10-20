package test.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import test.domain.Dormitory;
import test.domain.Student;
import test.util.JDBCUtils;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository
public class DorDao {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //宿舍管理
    public List<Dormitory> queryAll() throws Exception {
        String sql = "select * from dormitory";
        ResultSet rs = JDBCUtils.getMyDb().query(sql);
        List<Dormitory> list = new ArrayList<Dormitory>();
        while (rs.next()) {
            // 实例化Book对象
            Dormitory dormitory = new Dormitory();
            // 对id属性赋值
            dormitory.setId(rs.getInt("id"));

            list.add(dormitory);
        }
        return list;
    }

    //宿舍详情
    public List<Dormitory> queryOne(Integer i) throws Exception {
        String sql = "select * from dormitory where id = ?";
        ResultSet rs = JDBCUtils.getMyDb().query(sql, i);
        List<Dormitory> list = new ArrayList<Dormitory>();
        while (rs.next()) {
            Dormitory dormitory = new Dormitory();
            dormitory.setId(rs.getInt("id"));
            dormitory.setSex(rs.getString("sex"));
            dormitory.setNum(rs.getInt("num"));
            dormitory.setS1(rs.getInt("s1"));
            dormitory.setS2(rs.getInt("s2"));
            dormitory.setS3(rs.getInt("s3"));
            dormitory.setS4(rs.getInt("s4"));
            list.add(dormitory);
        }
        return list;
    }

    //修改回显
    public Dormitory queryOne2(Integer i) throws Exception {
        String sql = "select * from dormitory where id = ?";
        ResultSet rs = JDBCUtils.getMyDb().query(sql, i);
        Dormitory dormitory = new Dormitory();
        while (rs.next()) {

            dormitory.setId(rs.getInt("id"));
            dormitory.setSex(rs.getString("sex"));
            dormitory.setNum(rs.getInt("num"));
            dormitory.setS1(rs.getInt("s1"));
            dormitory.setS2(rs.getInt("s2"));
            dormitory.setS3(rs.getInt("s3"));
            dormitory.setS4(rs.getInt("s4"));
        }

        return dormitory;
    }

    //修改
    public void modify(Dormitory dormitory) throws Exception {
        String sql = "update dormitory set s1 = ?, s2 = ?, s3 = ?, s4 = ? where id = ?";
        JDBCUtils db = JDBCUtils.getMyDb();
        db.cud(sql, dormitory.getS1(), dormitory.getS2(), dormitory.getS3(), dormitory.getS4(), dormitory.getId());
    }
}
