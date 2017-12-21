package ru.apermyakov.servlets.testtask;

/**
 * Class for build user related entities specification.
 *
 * @author apermyakov
 * @version 1.0
 * @since 21.12.2017
 */
public class UserRelatedEntitiesSpecification implements Specification {

    /**
     * Field for save id.
     */
    private final int id;

    /**
     * Design specification.
     *
     * @param id
     */
    public UserRelatedEntitiesSpecification(int id) {
        this.id = id;
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
                                "where u.id=%s " +
                            "),',') " +
                        ") as music_types " +
                        "on true " +
                "where u.id=%s",
                this.id, this.id
        );
    }
}
