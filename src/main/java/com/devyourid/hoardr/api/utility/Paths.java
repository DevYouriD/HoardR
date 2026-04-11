package com.devyourid.hoardr.api.utility;

public final class Paths {

    /**
     * The base of all USER Thymeleaf paths.
     */
    public static final String BASE_PATH = "/";

    /**
     * User actions paths.
     */
    public static final String GET_SERIES_DETAILS_PATH = "/series/{seriesId}";

    public static final String GET_EXPANSION_SETS_DETAILS_PATH = "/series/{seriesId}/sets/{setId}";

    /**
     * The base of all ADMIN Thymeleaf paths.
     */
    public static final String ADMIN_BASE_PATH = "/admin";

    /**
     * Series actions paths.
     */
    private static final String SERIES_BASE_PATH = "/series";

    public static final String ADD_SERIES_PATH = SERIES_BASE_PATH + "/add";

    public static final String UPDATE_SERIES_PATH = SERIES_BASE_PATH + "/update";

    public static final String DELETE_SERIES_PATH = SERIES_BASE_PATH + "/delete";

    /**
     * Expansion Set actions paths.
     */
    private static final String EXPANSION_BASE_PATH = "/expansion";

    public static final String ADD_EXPANSION_PATH = EXPANSION_BASE_PATH + "/add";

    public static final String UPDATE_EXPANSION_PATH = EXPANSION_BASE_PATH + "/update";

    public static final String DELETE_EXPANSION_PATH = EXPANSION_BASE_PATH + "/delete";

    /**
     * Card actions paths.
     */
    private static final String CARD_BASE_PATH = "/card";

    public static final String ADD_CARD_PATH = CARD_BASE_PATH + "/add";

    public static final String UPDATE_CARD_PATH = CARD_BASE_PATH + "/update";

    public static final String DELETE_CARD_PATH = CARD_BASE_PATH + "/delete";

    /**
     * Redirect URLs.
     */
    public static final String ADMIN_PANEL_REDIRECT_URL = "redirect:/admin";

    private Paths() { /* Empty constructor to prohibit initialization. */ }
}
