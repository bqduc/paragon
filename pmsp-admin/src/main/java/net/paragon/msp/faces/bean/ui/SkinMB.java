package net.paragon.msp.faces.bean.ui;

import com.github.adminfaces.template.config.AdminConfig;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

import static com.github.adminfaces.template.util.Assert.has;

/**
 * Created by rmpestano on 07/01/17.
 */
/**
@Named
@SessionScoped
public class SkinMB implements Serializable {
	private static final long serialVersionUID = -1941764710323285178L;

		private String skin;

    @Inject
    AdminConfig adminConfig;

    @PostConstruct
    public void init() {
        skin = adminConfig.getSkin();
        if(!has(skin)) {
            skin = "skin-blue";
        }
    }


    public void changeSkin(String skin){
        this.skin = skin;
    }

    public String getSkin() {
        return skin;
    }

    public void setSkin(String skin) {
        this.skin = skin;
    }
}
*/