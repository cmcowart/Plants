package com.example.demo.constants;

public class SqlQueries {
    public static final String CREATE_PLANT = "INSERT INTO PLANTS(plant_type_id, plant_location_id, name, is_active) VALUES (?,?,?,1)";
    public static final String CREATE_PLANT_ACTION = "INSERT INTO PLANT_ACTIONS(plant_id, action_type_id, action_at) VALUES (?,?,?)";

    public static final String GET_ALL_PLANTS = "SELECT id, plant_type_id, plant_location_id, name, is_active FROM PLANTS";
    public static final String GET_ALL_PLANT_LOCATIONS = "SELECT id, description FROM PLANT_LOCATIONS";
    public static final String GET_ALL_PLANT_ACTIONS = "SELECT id, plant_id, action_type_id, action_at FROM PLANT_ACTIONS ORDER BY id";
    public static final String GET_MAX_PLANT_ID = "SELECT MAX(id) FROM PLANTS";
    public static final String GET_PLANT_ACTION_TYPES = "SELECT id, description FROM PLANT_ACTION_TYPES";
    public static final String GET_ACTIONS_FOR_PLANT = "" +
            "SELECT ID, PLANT_ID, ACTION_TYPE_ID, ACTION_AT " +
            "FROM PLANT_ACTIONS " +
            "WHERE PLANT_ID = ? " +
            "ORDER BY ACTION_AT DESC";
    public static final String GET_PLANT_REPORT = "" +
            "SELECT " +
            "    PLANTS.*, " +
            "    WATERINGS.WATERED_AT, " +
            "    FEEDINGS.FED_AT " +
            "FROM PLANTS " +
            "    LEFT JOIN ( " +
            "        SELECT " +
            "            PLANT_ID, " +
            "            MAX(ACTION_AT) AS WATERED_AT " +
            "        FROM PLANT_ACTIONS " +
            "        WHERE ACTION_TYPE_ID = 3 " +
            "        GROUP BY PLANT_ID " +
            "    ) WATERINGS ON PLANTS.ID = WATERINGS.PLANT_ID " +
            "    LEFT JOIN ( " +
            "        SELECT " +
            "            PLANT_ID, " +
            "            MAX(ACTION_AT) AS FED_AT " +
            "        FROM PLANT_ACTIONS " +
            "        WHERE ACTION_TYPE_ID = 4 " +
            "        GROUP BY PLANT_ID " +
            "    ) FEEDINGS ON PLANTS.ID = FEEDINGS.PLANT_ID " +
            "WHERE PLANTS.IS_ACTIVE = 1";
}
