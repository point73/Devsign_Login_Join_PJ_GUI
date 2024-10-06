package DAO;

import DTO.DevsignDTO;
import test.MemberDTO;

import javax.swing.*;
import java.sql.*;

public class DevsignDAO {
    private static final String DRIVER
            = "com.mysql.cj.jdbc.Driver";
    private static final String URL
            = "jdbc:mysql://localhost:3306/devsignprogram";
    private static final String USER = "root"; //DB ID
    private static final String PASS = "0000"; //DB 패스워드

    public DevsignDAO() {
    }

    /**
     * DB연결 메소드
     */
    public Connection getConn() {
        Connection con = null;

        try {
            Class.forName(DRIVER); //1. 드라이버 로딩
            con = DriverManager.getConnection(URL, USER, PASS); //2. 드라이버 연결

        } catch (Exception e) {
            e.printStackTrace();
        }

        return con;
    }

    /**
     * 회원 등록
     */
    public boolean insertMember(DevsignDTO dto) {
        boolean ok = false;

        Connection conn = null; // 연결
        PreparedStatement ps = null; //명령

        try {
            conn = getConn();
            String sql = "insert into table_member(" +
                    "id,pwd,name,tel,addr,birth," +
                    "major,gender,email) " +
                    "values(?,?,?,?,?,?,?,?,?)";

            ps = conn.prepareStatement(sql);
            ps.setString(1, dto.getId());
            ps.setString(2, dto.getPwd());
            ps.setString(3, dto.getName());
            ps.setString(4, dto.getTel());
            ps.setString(5, dto.getAddr());
            ps.setString(6, dto.getBirth());
            ps.setString(7, dto.getMajor());
            ps.setString(8, dto.getGender());
            ps.setString(9, dto.getEmail());
            int r = ps.executeUpdate(); //실행 -> 저장


            if (r > 0) {
                System.out.println("가입 성공");
                ok = true;
            } else {
                System.out.println("가입 실패");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ok;
    }//insertMember

    public boolean overlap(String id) {
        Connection conn = null; // 연결
        PreparedStatement ps = null; // 명령(실행통로)
        ResultSet rs = null; // 결과받기
        try {
            conn = getConn();
            String sql = "select * from table_member where id=? ";

            ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                rs.close();
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean loginResult(String id, String pwd) {
        Connection conn = null; // 연결
        PreparedStatement ps = null; // 명령(실행 경로)
        ResultSet rs = null; // 추출(결과받기)
        try {
            conn = getConn();
            String sql = "select * from table_member where id=? ";
            ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            rs = ps.executeQuery();

            if(rs.next()) {
                String user_id = rs.getString("id");
                String user_pwd = rs.getString("pwd");
                if(user_id.equals(id) && user_pwd.equals(pwd)) {
                    return true;
                }
                System.out.println(user_id + " " + pwd);
            } else {
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public DevsignDTO getMember(String id) {

        Connection conn = null; // 연결
        PreparedStatement ps = null; //명령
        ResultSet rs = null; // 추출(결과받기)

        try {
            conn = getConn();
            String sql = "select * from table_member where id=? ";

            ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            rs = ps.executeQuery();

            if(rs.next()) {
                DevsignDTO dto = new DevsignDTO();
                dto.setId(rs.getString("id"));
                dto.setPwd(rs.getString("pwd"));
                dto.setName(rs.getString("name"));
                dto.setTel(rs.getString("tel"));
                dto.setAddr(rs.getString("addr"));
                dto.setBirth(rs.getString("birth"));
                dto.setMajor(rs.getString("major"));
                dto.setGender(rs.getString("gender"));
                dto.setEmail(rs.getString("email"));
                return dto;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**회원정보 수정*/
    public boolean updateMember(DevsignDTO dto){
        System.out.println("dto="+dto.toString());
        boolean ok = false;
        Connection con = null;
        PreparedStatement ps = null;
        try{

            con = getConn();
            String sql = "update table_member set name=?, tel=?, addr=?, birth=?, major=?, gender=?" +
                    ", email=? "+ "where id=? and pwd=?";

            ps = con.prepareStatement(sql);

            ps.setString(1, dto.getName());
            ps.setString(2, dto.getTel());
            ps.setString(3, dto.getAddr());
            ps.setString(4, dto.getBirth());
            ps.setString(5, dto.getMajor());
            ps.setString(6, dto.getGender());
            ps.setString(7, dto.getEmail());
            ps.setString(8, dto.getId());
            ps.setString(9, dto.getPwd());

            int r = ps.executeUpdate(); //실행 -> 수정
            // 1~n: 성공 , 0 : 실패

            if(r>0) ok = true; //수정이 성공되면 ok값을 true로 변경

        }catch(Exception e){
            e.printStackTrace();
        }

        return ok;
    }

    /**회원정보 삭제 :
     *tip: 실무에서는 회원정보를 Delete 하지 않고 탈퇴여부만 체크한다.*/
    public boolean deleteMember(String id, String pwd){

        boolean ok =false ;
        Connection con =null;
        PreparedStatement ps =null;

        try {
            con = getConn();
            String sql = "delete from table_member where id=? and pwd=?";

            ps = con.prepareStatement(sql);
            ps.setString(1, id);
            ps.setString(2, pwd);
            int r = ps.executeUpdate(); // 실행 -> 삭제

            if (r>0) ok=true; //삭제됨;

        } catch (Exception e) {
            System.out.println(e + "-> 오류발생");
        }
        return ok;
    }
}
