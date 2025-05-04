(function ($) {
    //Handle reload page when select page limit
    $('#limit_select').on('change', function() {
        console.log("changed hehehe");

        var selectedLimit = $(this).val();

        const currentUrl = new URL(window.location.href);
        const searchParams = currentUrl.searchParams;

        searchParams.set('limit', selectedLimit);
        searchParams.set('page', 1);

        window.location.href = currentUrl.toString();

    });

    

})(jQuery);
