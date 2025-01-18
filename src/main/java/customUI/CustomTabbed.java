package customUI;

import java.awt.Component;

import javax.swing.Icon;

public class CustomTabbed{
	private String textTab;
	private Icon pathIcon = null;
	private String pathIconLink = null;
	private Component component;
	private String tip;
	
	
	public String getTextTab() {
		return textTab;
	}

	public Icon getPathIcon() {
		return pathIcon;
	}

	public Component getComponent() {
		return component;
	}

	public String getTip() {
		return tip;
	}

	public String getPathIconLink() {
		return pathIconLink;
	}

	public void setTextTab(String textTab) {
		this.textTab = textTab;
	}

	public void setPathIcon(Icon pathIcon) {
		this.pathIcon =  pathIcon ;
	}

	public void setPathIconLink(String pathIconLink) {
		this.pathIconLink = pathIconLink;
	}

	public void setComponent(Component component) {
		this.component = component;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}
	public CustomTabbed(String textTab, String pathIconLink , Component component, String tip) {
		super();
		this.textTab = textTab;
		this.pathIconLink = pathIconLink;
		this.component = component;
		this.tip = tip;
		
		setTextTab("<html><body leftmargin=15 topmargin=5 marginwidth=15 marginheight=5>"+textTab+"</body></html>");
		setPathIcon(new ImageScaler("/icon/" + pathIconLink , 22, 22).getScaledImageIcon());
	}

	
	
}
