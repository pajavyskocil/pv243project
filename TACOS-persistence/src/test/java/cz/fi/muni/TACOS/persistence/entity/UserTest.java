package cz.fi.muni.TACOS.persistence.entity;

import cz.fi.muni.TACOS.persistence.enums.UserRole;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ArchivePaths;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;


@RunWith(Arquillian.class)
public class UserTest {


    @Deployment
    public static Archive<?> createDeployment() {
        return ShrinkWrap
                .create(WebArchive.class)
                .addPackages(true, "cz.fi.muni.TACOS.persistence")
                .addAsManifestResource("test-persistence.xml", ArchivePaths.create("persistence.xml"));
    }

    private User setUpUserPavel() {
        return new User("Pavel", "Vyskocil", "pa.vy@gmail.com", UserRole.SUPERADMIN);
    }

    @Test
    public void getName() throws Exception {
        User user = setUpUserPavel();

        user.setName("Pavel");
        Assert.assertEquals("Pavel", user.getName());
    }
}
