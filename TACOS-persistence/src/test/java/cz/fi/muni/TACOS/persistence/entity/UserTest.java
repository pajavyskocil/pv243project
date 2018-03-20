package cz.fi.muni.TACOS.persistence.entity;

import cz.fi.muni.TACOS.persistence.enums.UserRole;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ArchivePath;
import org.jboss.shrinkwrap.api.ArchivePaths;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import javax.jws.soap.SOAPBinding;


@RunWith(Arquillian.class)
public class UserTest {


    @Deployment
    public static Archive<?> createDeployment() {
        return ShrinkWrap.create(WebArchive.class)
                .addClasses(User.class)
                .addAsManifestResource("test-persistence.xml", ArchivePaths.create("persistence.xml"));
    }

    @Inject
    User user;


    @Test
    public void getName() throws Exception{
        //User user = new User("Pavel", "sss", "sss", UserRole.SUPERADMIN);
        user.setName("Pavel");
        Assert.assertEquals("Pavel", user.getName());
    }

}
