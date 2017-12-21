package ru.apermyakov.servlets.testtask;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class UserRepositoryTest {

    @Test
    public void checkSearchByIdUserRepository() {
        UserRepository repository = new UserRepository();
        UserRelatedEntitiesSpecification specification = new UserRelatedEntitiesSpecification(1);
        assertThat(repository.query(specification).get(0).getMusicType().get(2), is("SYNTHWAVE"));
    }

    @Test
    public void checkSearchByAddressUserRepository() {
        UserRepository repository = new UserRepository();
        UserSearchByParamSpecification specification = new UserSearchByParamSpecification("address", "Vas. bor 3");
        assertThat(repository.query(specification).get(0).getMusicType().get(2), is("SYNTHWAVE"));
    }

    @Test
    public void checkSearchByRoleUserRepository() {
        UserRepository repository = new UserRepository();
        UserSearchByParamSpecification specification = new UserSearchByParamSpecification("role", "USER");
        assertThat(repository.query(specification).get(0).getMusicType().get(2), is("SYNTHWAVE"));
    }

    @Test
    public void checkSearchByMusicTypeUserRepository() {
        UserRepository repository = new UserRepository();
        UserSearchByParamSpecification specification = new UserSearchByParamSpecification("musictype", "CLASSICAL");
        assertThat(repository.query(specification).get(0).getMusicType().get(2), is("SYNTHWAVE"));
    }
}