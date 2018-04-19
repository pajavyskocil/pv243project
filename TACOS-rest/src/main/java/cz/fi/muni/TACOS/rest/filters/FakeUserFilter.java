package cz.fi.muni.TACOS.rest.filters;

import cz.fi.muni.TACOS.dto.UserCreateDTO;
import cz.fi.muni.TACOS.dto.UserDTO;
import cz.fi.muni.TACOS.enums.UserRole;
import cz.fi.muni.TACOS.facade.UserFacade;
import cz.fi.muni.TACOS.rest.ApiUris;

import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = ApiUris.REST_ROOT + "/*")
public class FakeUserFilter implements Filter {

    public static final String FAKE_USER = "fake_user";

    @Inject
    private UserFacade userFacade;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        //TODO : REMOVE THIS WHEN SECURITY IS IMPLEMENTED
        UserDTO fakeUser = userFacade.findByEmail("fake@user.cz");
        if (fakeUser == null) {
            UserCreateDTO fakeUserCreate = new UserCreateDTO();
            fakeUserCreate.setEmail("fake@user.cz");
            fakeUserCreate.setName("fake");
            fakeUserCreate.setSurname("user");
            fakeUserCreate.setRole(UserRole.SUPERADMIN);
            fakeUserCreate.setPassword("fakeuser");

            userFacade.create(fakeUserCreate);
            fakeUser = userFacade.findByEmail("fake@user.cz");
        }

        request.setAttribute(FAKE_USER, fakeUser);

        filterChain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}
