package DevsignProject;

import DAO.DevsignDAO;
import DTO.DevsignDTO;
import test.MemberDAO;
import test.MemberDTO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.StringTokenizer;

public class DevsignProgram extends BaseClass implements ActionListener {
    JTextField tfMajor, tfGender;

    public DevsignProgram(DevsignDTO dto) {
        System.out.println("Devsign Program 실행");
        this.setTitle("내 정보");
        gb = new GridBagLayout();
        setLayout(gb);
        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;

        // 아이디
        JLabel bId = new JLabel("아이디 : ");
        tfId = new JTextField(dto.getId(),20);
        tfId.setEditable(false);
        //btnIdOverlap = new JButton("중복확인");
        gbAdd(bId, 0,0,1,1);
        gbAdd(tfId, 1,0,3,1);
        //gbAdd(btnIdOverlap, 2,0,1,1);

        // 비밀번호
        JLabel bPwd = new JLabel("비밀번호 : ");
        pfPwd = new JPasswordField(20);
        gbAdd(bPwd, 0, 1, 1, 1);
        gbAdd(pfPwd, 1, 1, 3, 1);

        // 이름
        JLabel bName = new JLabel("이름 :");
        tfName = new JTextField(dto.getName(),20);
        tfName.setEditable(false);
        gbAdd(bName, 0, 2, 1, 1);
        gbAdd(tfName, 1, 2, 3, 1);

        //전화
        JLabel bTel = new JLabel("전화 :");
        JPanel pTel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        StringTokenizer st = new StringTokenizer(dto.getTel(),"-");
        tfTel1 = new JTextField(st.nextToken(),6);
        tfTel2 = new JTextField(st.nextToken(),6);
        tfTel3 = new JTextField(st.nextToken(),6);
        pTel.add(tfTel1);
        pTel.add(new JLabel(" - "));
        pTel.add(tfTel2);
        pTel.add(new JLabel(" - "));
        pTel.add(tfTel3);
        gbAdd(bTel, 0, 3, 1,1);
        gbAdd(pTel, 1, 3, 3,1);

        //주소
        JLabel bAddr = new JLabel("주소: ");
        tfAddr = new JTextField(dto.getAddr(),20);
        gbAdd(bAddr, 0,4,1,1);
        gbAdd(tfAddr, 1, 4, 3,1);

        //생일
        JLabel bBirth= new JLabel("생년월일: ");
        String sBirth = dto.getBirth();
        tfYear = new JTextField(sBirth.substring(0,4),6);
        tfMonth = new JTextField(sBirth.substring(4,6),6);
        tfDate = new JTextField(sBirth.substring(6,8),6);
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
        tfMajor = new JTextField(dto.getMajor(),6);
        //JPanel pMajor = new JPanel(new FlowLayout(FlowLayout.LEFT));
        //pMajor.add(tfMajor);
        tfMajor.setEditable(false);
        gbAdd(bMajor, 0,6,1,1);
        gbAdd(tfMajor,1,6,3,1);

        //성별
        JLabel bGender = new JLabel("성별 : ");
        //JPanel pGender = new JPanel(new FlowLayout(FlowLayout.LEFT));
        //rbMan = new JRadioButton("남",true);
        //rbWoman = new JRadioButton("여",true);
        tfGender = new JTextField(dto.getGender(),6);
        //ButtonGroup group = new ButtonGroup();
        //group.add(rbMan);
        //group.add(rbWoman);
        //pGender.add(rbMan);
        //pGender.add(rbWoman);
        tfGender.setEditable(false);
        gbAdd(bGender, 0,7,1,1);
        gbAdd(tfGender,1,7,3,1);

        //이메일
        JLabel bEmail = new JLabel("이메일 : ");
        tfEmail = new JTextField(dto.getEmail(),20);
        gbAdd(bEmail, 0,8,1,1);
        gbAdd(tfEmail,1,8,3,1);

        //버튼
        JPanel pButton = new JPanel();
        //btnInsert = new JButton("가입");
        //btnCancel = new JButton("취소");
        btnUpdate = new JButton("수정");
        btnDelete = new JButton("탈퇴");
        //pButton.add(btnInsert); // 가입
        //pButton.add(btnCancel); // 취소
        pButton.add(btnUpdate); // 수정
        pButton.add(btnDelete); // 탈퇴
        gbAdd(pButton, 0, 10, 4, 1);


        //btnInsert.addActionListener(this); // 가입버튼 감지기
        //btnCancel.addActionListener(this); // 취소버튼 감지기
        //btnIdOverlap.addActionListener(this); // 중복버튼 감지기
        btnUpdate.addActionListener(this);
        btnDelete.addActionListener(this);

        setSize(355, 450);
        setLocationRelativeTo(null); //화면 가운데에 배치
        //setResizable(false); //사이즈 고정
        setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 프로그램 종료가 아닌 '현재 창만 닫기'
    }

    public static void main(String[] args) {
        //new DevsignProgram();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btnUpdate){
            UpdateMember();
        }else if(e.getSource() == btnDelete){
            //int x = JOptionPane.showConfirmDialog(this,"정말 삭제하시겠습니까?");
            int x = JOptionPane.showConfirmDialog(this,"정말 삭제하시겠습니까?","삭제",JOptionPane.YES_NO_OPTION);

            if (x == JOptionPane.OK_OPTION){
                deleteMember();
            }else{
                JOptionPane.showMessageDialog(this, "삭제를 취소하였습니다.");
            }
        }
    }

    private void UpdateMember() {

        //1. 화면의 정보를 얻는다.
        DevsignDTO dto = getViewData();
        //2. 그정보로 DB를 수정
        DevsignDAO dao = new DevsignDAO();
        boolean ok = dao.updateMember(dto);
        if(dto.getPwd().length() == 0){
            JOptionPane.showMessageDialog(this, "비밀번호를 꼭 입력하세요!");
            return; //메소드 끝
        }

        if(ok){
            JOptionPane.showMessageDialog(this, "수정되었습니다.");
            //this.dispose();
        }else{
            JOptionPane.showMessageDialog(this, "수정실패");
        }
    }

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
        String major = tfMajor.getText();
        String gender = tfGender.getText();

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

    private void deleteMember() {
        String id = tfId.getText();
        String pwd = pfPwd.getText();
        if(pwd.length()==0){ //길이가 0이면

            JOptionPane.showMessageDialog(this, "비밀번호를 꼭 입력하세요!");
            return; //메소드 끝
        }
        //System.out.println(mList);
        DevsignDAO dao = new DevsignDAO();
        boolean ok = dao.deleteMember(id, pwd);

        if(ok){
            JOptionPane.showMessageDialog(this, "삭제완료");

        }else{
            JOptionPane.showMessageDialog(this, "삭제실패");

        }

    }//deleteMember
}
