package DevsignProject;

import DAO.DevsignDAO;
import DTO.DevsignDTO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class JoinProgram extends BaseClass implements ActionListener, MouseListener {
    boolean idoverlap = false, idoverlap_result = false;
    public JoinProgram() {
        System.out.println("Join Program 실행");
        this.setTitle("회원가입 프로그램");
        gb = new GridBagLayout();
        setLayout(gb);
        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;

        // 아이디
        JLabel bId = new JLabel("아이디 : ");
        tfId = new JTextField(15);
        btnIdOverlap = new JButton("중복확인");
        gbAdd(bId, 0,0,1,1);
        gbAdd(tfId, 1,0,1,1);
        gbAdd(btnIdOverlap, 2,0,1,1);

        // 비밀번호
        JLabel bPwd = new JLabel("비밀번호 : ");
        pfPwd = new JPasswordField(20);
        gbAdd(bPwd, 0, 1, 1, 1);
        gbAdd(pfPwd, 1, 1, 3, 1);

        // 이름
        JLabel bName = new JLabel("이름 :");
        tfName = new JTextField(20);
        gbAdd(bName, 0, 2, 1, 1);
        gbAdd(tfName, 1, 2, 3, 1);

        //전화
        JLabel bTel = new JLabel("전화 :");
        JPanel pTel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        tfTel1 = new JTextField("010",6);
        tfTel2 = new JTextField(6);
        tfTel3 = new JTextField(6);
        pTel.add(tfTel1);
        pTel.add(new JLabel(" - "));
        pTel.add(tfTel2);
        pTel.add(new JLabel(" - "));
        pTel.add(tfTel3);
        gbAdd(bTel, 0, 3, 1,1);
        gbAdd(pTel, 1, 3, 3,1);

        //주소
        JLabel bAddr = new JLabel("주소: ");
        tfAddr = new JTextField(20);
        gbAdd(bAddr, 0,4,1,1);
        gbAdd(tfAddr, 1, 4, 3,1);

        //생일
        JLabel bBirth= new JLabel("생년월일: ");
        tfYear = new JTextField(6);
        tfMonth = new JTextField(6);
        tfDate = new JTextField(6);
        JPanel pBirth = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pBirth.add(tfYear);
        pBirth.add(new JLabel("/"));
        pBirth.add(tfMonth);
        pBirth.add(new JLabel("/"));
        pBirth.add(tfDate);
        gbAdd(bBirth, 0,5,1,1);
        gbAdd(pBirth, 1, 5, 3,1);

        //학과
        JLabel bMajor = new JLabel("학과 : ");
        String[] arrMajor = {"---", "컴퓨터공학과", "전자공학부 전자공학전공", "전자공학부 IoT전공"};
        cbMajor = new JComboBox(arrMajor);
        JPanel pMajor = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pMajor.add(cbMajor);
        gbAdd(bMajor, 0,6,1,1);
        gbAdd(pMajor,1,6,3,1);

        //성별
        JLabel bGender = new JLabel("성별 : ");
        JPanel pGender = new JPanel(new FlowLayout(FlowLayout.LEFT));
        rbMan = new JRadioButton("남",true);
        rbWoman = new JRadioButton("여",true);
        ButtonGroup group = new ButtonGroup();
        group.add(rbMan);
        group.add(rbWoman);
        pGender.add(rbMan);
        pGender.add(rbWoman);
        gbAdd(bGender, 0,7,1,1);
        gbAdd(pGender,1,7,3,1);

        //이메일
        JLabel bEmail = new JLabel("이메일 : ");
        tfEmail = new JTextField(20);
        gbAdd(bEmail, 0,8,1,1);
        gbAdd(tfEmail,1,8,3,1);

        //버튼
        JPanel pButton = new JPanel();
        btnInsert = new JButton("가입");
        btnCancel = new JButton("취소");
        //btnUpdate = new JButton("수정");
        //btnDelete = new JButton("탈퇴");
        pButton.add(btnInsert); // 가입
        pButton.add(btnCancel); // 취소
        //pButton.add(btnUpdate);
        //pButton.add(btnDelete);
        gbAdd(pButton, 0, 10, 4, 1);


        btnInsert.addActionListener(this); // 가입버튼 감지기
        btnCancel.addActionListener(this); // 취소버튼 감지기
        btnIdOverlap.addActionListener(this);
        btnIdOverlap.addMouseListener(this);// 중복버튼 감지기
        //btnUpdate.addActionListener(this);
        //btnDelete.addActionListener(this);

        setSize(355, 450);
        setLocationRelativeTo(null); //화면 가운데에 배치
        //setResizable(false); //사이즈 고정
        setVisible(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // 프로그램 종료가 아닌 '현재 창만 닫기'
    }

    public static void main(String[] args) {
        new JoinProgram();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //boolean idoverlap_result = false;
        if(e.getSource() == btnInsert){ // 가입
            if(tfId.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "아이디를 입력해주세요."); // 알림창
                return;
            } else if(pfPwd.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "비밀번호를 입력해주세요."); // 알림창
                return;
            } else if(tfName.getText().isEmpty()){
                JOptionPane.showMessageDialog(this, "이름을 입력해주세요."); // 알림창
                return;
            } else if(tfTel2.getText().isEmpty() || tfTel3.getText().isEmpty()){
                JOptionPane.showMessageDialog(this, "전화번호를 입력해주세요.."); // 알림창
                return;
            } else if(tfAddr.getText().isEmpty()){
                JOptionPane.showMessageDialog(this, "주소를 입력해주세요.."); // 알림창
                return;
            } else if(tfYear.getText().isEmpty() || tfMonth.getText().isEmpty() || tfDate.getText().isEmpty()){
                JOptionPane.showMessageDialog(this, "생년월일을 입력해주세요.."); // 알림창
                return;
            } else if(tfEmail.getText().isEmpty()){
                JOptionPane.showMessageDialog(this, "이메일을 입력해주세요.."); // 알림창
                return;
            }
            if(!idoverlap){
                JOptionPane.showMessageDialog(this, "중복확인을 해주시기 바랍니다."); // 알림창
                return;
            }
            if(idoverlap && idoverlap_result) {
                insertMember();
                //idoverlap_result = false;
            } else {
                JOptionPane.showMessageDialog(this, "가입이 정상적으로 처리되지 않았습니다."); // 알림창
            }
            System.out.println("insertMember() 호출 종료");
        }else if(e.getSource() == btnCancel){ // 취소
            this.dispose(); //창닫기 (현재창만 닫힘)
            //system.exit(0)=> 내가 띄운 모든 창이 다 닫힘
        } /*else if (e.getSource() == btnIdOverlap){ // 중복검사
            idoverlap_result = IdOverlap();
            System.out.println("IdOverlap 호출 종료");
        }*//*else if(e.getSource() == btnUpdate){
            UpdateMember();
        }else if(e.getSource() == btnDelete){
            //int x = JOptionPane.showConfirmDialog(this,"정말 삭제하시겠습니까?");
            int x = JOptionPane.showConfirmDialog(this,"정말 삭제하시겠습니까?","삭제",JOptionPane.YES_NO_OPTION);

            if (x == JOptionPane.OK_OPTION){
                deleteMember();
            }else{
                JOptionPane.showMessageDialog(this, "삭제를 취소하였습니다.");
            }
        }*/
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //if(e.getClickCount() == 1)
        if(e.getClickCount() >= 1){
            idoverlap_result = IdOverlap();
            System.out.println("IdOverlap 호출 종료");
            idoverlap = true;
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    private void insertMember(){
        DevsignDTO devsignDTO = getViewData();
        DevsignDAO devsignDAO = new DevsignDAO();
        boolean ok = devsignDAO.insertMember(devsignDTO);

        if(ok){

            JOptionPane.showMessageDialog(this, "가입이 완료되었습니다."); // 알림창
            dispose();

        }else{

            JOptionPane.showMessageDialog(this, "가입이 정상적으로 처리되지 않았습니다."); // 알림창
        }
    }
    //private void UpdateMember(){}
    //private void deleteMember(){}

    private DevsignDTO getViewData(){

        //화면에서 사용자가 입력한 내용을 얻는다.
        DevsignDTO dto = new DevsignDTO();
        String id = tfId.getText();
        String pwd = pfPwd.getText();
        String name = tfName.getText();
        String tel1 = tfTel1.getText();
        String tel2 = tfTel2.getText();
        String tel3 = tfTel3.getText();
        String tel = tel1+"-"+tel2+"-"+tel3;
        String addr = tfAddr.getText();
        String birth1 = tfYear.getText();
        String birth2 = tfMonth.getText();
        String birth3 = tfDate.getText();
        //String birth = birth1+"/"+birth2+"/"+birth3;
        String birth = birth1+birth2+birth3;
        String major = (String)cbMajor.getSelectedItem();
        String gender = "";
        if(rbMan.isSelected()){
            gender = "M";
        }else if(rbWoman.isSelected()){
            gender = "W";
        }

        String email = tfEmail.getText();

        //dto에 담는다.
        dto.setId(id);
        dto.setPwd(pwd);
        dto.setName(name);
        dto.setTel(tel);
        dto.setAddr(addr);
        dto.setBirth(birth);
        dto.setMajor(major);
        dto.setGender(gender);
        dto.setEmail(email);

        return dto;
    }

    private boolean IdOverlap(){
        String id = tfId.getText();
        DevsignDAO devsignDAO = new DevsignDAO();
        boolean ok = devsignDAO.overlap(id);

        if(id.length()== 0){
            JOptionPane.showMessageDialog(this, "아이디를 입력해주세요."); // 알림창
            return false;
        }else {
            if (ok) {
                JOptionPane.showMessageDialog(this, "사용할 수 없는 아이디입니다.");
                return false;
            } else {
                JOptionPane.showMessageDialog(this, id + " 는 사용 가능한 아이디입니다.");
                return true;
            }
        }
    }
}
