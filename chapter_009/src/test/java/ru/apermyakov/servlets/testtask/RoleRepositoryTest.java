package ru.apermyakov.servlets.testtask;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class RoleRepositoryTest {

    @Test
    public void checkRoleRepositoryRelativeEntitiesByFirstRole() {
        RoleRepository repository = new RoleRepository();
        RoleRelatedEntitiesSpecification specification = new RoleRelatedEntitiesSpecification("USER");
        assertThat(repository.query(specification).get(0).getMusicType().get(2), is("SYNTHWAVE"));
    }

    @Test
    public void checkRoleRepositoryRelativeEntitiesBySecondRole() {
        RoleRepository repository = new RoleRepository();
        RoleRelatedEntitiesSpecification specification = new RoleRelatedEntitiesSpecification("MANDATOR");
        assertThat(repository.query(specification).get(0).getMusicType().get(0), is("HOUSE"));
    }

}