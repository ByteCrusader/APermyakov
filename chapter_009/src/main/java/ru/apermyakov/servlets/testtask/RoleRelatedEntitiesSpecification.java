package ru.apermyakov.servlets.testtask;

/**
 * Class for build role related entities specification.
 *
 * @author apermyakov
 * @version 1.0
 * @since 21.12.2017
 */
public class RoleRelatedEntitiesSpecification implements Specification {

    /**
     * Field for save role.
     */
    private String role;

    /**
     * Design specification.
     *
     * @param role
     */
    public RoleRelatedEntitiesSpecification(String role) {
        this.role = role;
    }

    /**
     * Method for build sql script.
     *
     * @return script
     */
    @Override
    public String toSqlString() {

        return String.format(
                "select u.id, u.name, r.name as role, " +
                        "a.name as address, music_types " +
                        "from users as u " +
                        "left join role as r " +
                        "on u.role_id=r.id " +
                        "left join address as a " +
                        "on u.address_id=a.id " +
                        "left join (" +
                        "select array_to_string(ARRAY(" +
                        "select m.name " +
                        "from users as u " +
                        "left join users_musictype as um " +
                        "on u.id=um.user_id " +
                        "left join musictype as m " +
                        "on m.id=um.musictype_id " +
                        "where u.role_id = (" +
                        "select id from role " +
                        "where name='%s')" +
                        "),',') " +
                        ") as music_types " +
                        "on true " +
                        "where u.role_id = (" +
                        "select id from role " +
                        "where name='%s')",
                this.role, this.role
        );
    }
}
