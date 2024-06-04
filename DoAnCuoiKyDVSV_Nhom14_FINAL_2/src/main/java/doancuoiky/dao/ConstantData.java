package doancuoiky.dao;

import java.util.ArrayList;
import java.util.List;

import doancuoiky.dao.NguoiDungDao;
import doancuoiky.model.NguoiDung;

public class ConstantData {
	public static int countSTT;
	
	public static class MailAddress {
		protected String owner;
		protected String username;
		protected String password;
		public MailAddress(String owner, String user, String pass) {
			super();
			this.owner=owner;
			this.username=user;
			this.password=pass;
		}
		public String getOwner() {return this.owner;};
		public void setOwner(String owner) {this.owner=owner;};
		public String getUsername() {return this.username;};
		public void setUsername(String user) {this.username=user;};
		public String getPassword() {return this.password;};
		public void setPassword(String pass) {this.password=pass;};
	};
	public static class CTSVMail extends MailAddress {
		public CTSVMail(String owner, String user, String pass) {
			super(owner, user, pass);
			this.username=user;
			this.password=pass;
		}
	};
	
	public static class QTVMail extends MailAddress {
		public QTVMail(String owner, String user, String pass) {
			super(owner, user, pass);
			this.username=user;
			this.password=pass;
		}
	};
	
	public static class SVMail extends MailAddress {
		public SVMail(String owner, String user, String pass) {
			super(owner, user, pass);
			this.username=user;
			this.password=pass;
		}
	};
	
	public static SVMail sv01 = new SVMail("21110603","nva8720@gmail.com", "dboy ipte cnfx ljri");
	public static CTSVMail ctv01 = new CTSVMail("CTSV001", "tranthib1992@gmail.com", "tboq egpt edhk taxn");
	public static QTVMail qtv01 = new QTVMail("QTVHT001", "levanc1988@gmail.com", "uggl gpcs fwud xbek");
	public static SVMail sv02 = new SVMail("23141953", "ptd100395@gmail.com", "sbcg pbsz aiyu ohug");
	
	//public interface List<E> extends Collection<E>;
	// Set username password cho danh sách mail của CTSV ở đây
	public static List<CTSVMail> CTSVMailList = new ArrayList<CTSVMail>();
	// Tương tự cho ds mail của QTV
	public static List<QTVMail> QTVMailList = new ArrayList<QTVMail>();
	// Tương tự cho ds mail của SV 
	public static List<SVMail> SVMailList = new ArrayList<SVMail>();
	//SVMailList.add(nv01);
}