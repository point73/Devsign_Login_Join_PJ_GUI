package DevsignProject;

import javax.swing.*;
import java.awt.*;

public class BaseClass extends JFrame{
    //JPanel p;
    JTextField tfId, tfName, tfAddr, tfEmail;
    JTextField tfTel1, tfTel2, tfTel3; //전화
    JComboBox cbMajor; //학과
    JPasswordField pfPwd; //비밀번호
    JTextField tfYear, tfMonth, tfDate; //생년월일
    JRadioButton rbMan, rbWoman; //남, 여
    JButton btnIdOverlap; // 아이디 중복검사 버튼
    JButton btnInsert, btnCancel, btnUpdate,btnDelete; //가입, 취소, 수정 , 탈퇴 버튼

    GridBagLayout gb;
    GridBagConstraints gbc;

    public BaseClass() {
        // 타이틀 사진
        Toolkit kit = Toolkit.getDefaultToolkit();
        Image icon = kit.getImage("Dev.png");
        setIconImage(icon);
    }

    protected void gbAdd(JComponent c, int x, int y, int w, int h) {
        gbc.gridx = x; // 열 인덱스
        gbc.gridy = y; // 행 인덱스
        gbc.gridwidth = w; // 가로로 차지하는 셀 수
        gbc.gridheight = h; // 세로로 차지하는 셀 수
        gb.setConstraints(c, gbc);
        gbc.insets = new Insets(5, 5, 5, 5); // 컴포넌트 주위 여백(px)지정
        add(c, gbc);
    }
}
