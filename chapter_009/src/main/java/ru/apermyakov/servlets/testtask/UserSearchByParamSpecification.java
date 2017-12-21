package ru.apermyakov.servlets.testtask;

/**
 * Class for user search by param specification.
 *
 * @author apermyakov
 * @version 1.0
 * @since 21.12.2017
 */
public class UserSearchByParamSpecification implements Specification {

    /**
     * Field for parameter.
     */
    private String parameter;

    /**
     * Field for value.
     */
    private String value;

    /**
     * Design specification.
     *
     * @param parameter
     * @param value
     */
    public UserSearchByParamSpecification(String parameter, String value) {
        this.parameter = parameter;
        this.value = value;
    }

    /**
     * Method for convert param to column in user table.
     *
     * @return
     */
    private String convertParams() {
        StringBuilder result = new StringBuilder();
        if (this.parameter.toLowerCase().contains("address")) {
            result.append("u.address_id");
        } else
        if (this.parameter.toLowerCase().contains("role")) {
            result.append("u.role_id");
        } else
        if (this.parameter.toLowerCase().contains("music")) {
            result.append("u.id");
        }
        return result.toString();
    }

    /**
     * Method for build select by parameter and value.
     *
     * @return
     */
    private String buildSelect() {
        StringBuilder result = new StringBuilder();
        if (this.parameter.toLowerCase().contains("address")) {
            result.append("(select id from address where name='");
            result.append(this.value);
            result.append("')");
        } else
        if (this.parameter.toLowerCase().contains("role")) {
            result.append("(select id from role where name='");
            result.append(this.value);
            result.append("')");
        } else
        if (this.parameter.toLowerCase().contains("music")) {
            result.append("(select um.user_id from users_musictype as um ");
            result.append("left join musictype as m ");
            result.append("on um.musictype_id=m.id ");
            result.append("where m.name='");
            result.append(this.value);
            result.append("')");
        }
        return result.toString();
    }

    /**
     * Method for build result sql script.
     *
     * @return
     */
    @Override
    public String toSqlString() {
        String param = convertParams();
        String select = buildSelect();

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
                            "where %s = %s" +
                        "),',') " +
                        ") as music_types " +
                        "on true " +
                "where %s = %s",
                param, select, param, select
        );
    }
}
