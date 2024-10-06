package DevsignProject;

import DAO.DevsignDAO;
import DTO.DevsignDTO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginProgram extends BaseClass implements ActionListener {
    JButton btnJoin, btnLogin; //가입, 로그인

    public LoginProgram() {
        createUI();
    }

    //TODO 메인 메소드 시작
    public static void main(String[] args) {
        new LoginProgram();
    }

    private void createUI() {
        System.out.println("DevsignProject - 로그인 & 회원가입 프로그램 만들기");
        this.setTitle("Devsign 동아리 프로젝트");
        gb = new GridBagLayout();
        setLayout(gb);
        gbc = new GridBagConstraints();
        //gbc.fill = GridBagConstraints.CENTER;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;

        // 아이디
        JLabel bId = new JLabel("아이디 : ");
        tfId = new JTextField(20);
        gbAdd(bId, 0, 0, 1, 1);
        gbAdd(tfId, 1, 0, 1, 1);

        // 비밀번호
        JLabel bPwd = new JLabel("비밀번호 : ");
        pfPwd = new JPasswordField(20);
        gbAdd(bPwd, 0, 1, 1, 1);
        gbAdd(pfPwd, 1, 1, 1, 1);

        // 버튼
        JPanel pButton = new JPanel();
        btnLogin = new JButton("로그인");
        btnJoin = new JButton("가입");
        pButton.add(btnLogin);
        pButton.add(btnJoin);
        gbAdd(pButton, 0, 10, 4, 1);

        // 감지기
        btnLogin.addActionListener(this);
        //btnLogin.addKeyListener(this); //Enter 눌렀을 시 로그인 함수 호출 해보려다 실패.
        btnJoin.addActionListener(this);

        setSize(330, 155);
        setLocationRelativeTo(null); //화면 가운데에 배치
        setResizable(false); //사이즈 고정
        setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == btnLogin) {
            LoginMember();
            System.out.println("JoinMember() 호출 종료");
        } else if (ae.getSource() == btnJoin) {
            new JoinProgram();
        }
    }//actionPerformed

    private void LoginMember() {
        DevsignDAO devsignDAO = new DevsignDAO();
        String id = tfId.getText();
        String pwd = pfPwd.getText();
        boolean ok = devsignDAO.loginResult(id, pwd);
        if (ok) {
            //JOptionPane.showMessageDialog(this, "로그인 성공"); // 알림창
            setVisible(false);
            DevsignDTO dto = devsignDAO.getMember(id);
            if(dto == null) {
                JOptionPane.showMessageDialog(this, "로그인 실패");
            }
            new DevsignProgram(dto);
        } else {
            JOptionPane.showMessageDialog(this, "로그인 실패"); // 알림창
        }


    }
    /*private void deleteMember() {
        String id = tfId.getText();
        String pwd = pfPwd.getText();
        if(pwd.length()==0){ //길이가 0이면

            JOptionPane.showMessageDialog(this, "비밀번호를 꼭 입력하세요!");
            return; //메소드 끝
        }
        //System.out.println(mList);
        MemberDAO dao = new MemberDAO();
        boolean ok = dao.deleteMember(id, pwd);

        if(ok){
            JOptionPane.showMessageDialog(this, "삭제완료");
            dispose();

        }else{
            JOptionPane.showMessageDialog(this, "삭제실패");

        }

    }//deleteMember

    private void UpdateMember() {

        //1. 화면의 정보를 얻는다.
        MemberDTO dto = getViewData();
        //2. 그정보로 DB를 수정
        MemberDAO dao = new MemberDAO();
        boolean ok = dao.updateMember(dto);

        if(ok){
            JOptionPane.showMessageDialog(this, "수정되었습니다.");
            this.dispose();
        }else{
            JOptionPane.showMessageDialog(this, "수정실패: 값을 확인하세요");
        }
    }

    private void insertMember(){

        //화면에서 사용자가 입력한 내용을 얻는다.
        MemberDTO dto = getViewData();
        MemberDAO dao = new MemberDAO();
        boolean ok = dao.insertMember(dto);

        if(ok){

            JOptionPane.showMessageDialog(this, "가입이 완료되었습니다.");
            dispose();

        }else{

            JOptionPane.showMessageDialog(this, "가입이 정상적으로 처리되지 않았습니다.");
        }
    }//insertMember*/
}

