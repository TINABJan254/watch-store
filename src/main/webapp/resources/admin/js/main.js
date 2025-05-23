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

    //format time
    $(document).ready(function() {
        console.log('ready');
    
        function pad(num) {
            return num < 10 ? '0' + num : num;
        }
    
        $('.createTime').each(function() {
            var rawDateStr = $(this).text().trim();
            if(rawDateStr) {
                var dateObj = new Date(rawDateStr);
                if (!isNaN(dateObj)) {
                    var formattedDate = pad(dateObj.getDate()) + '/' +
                                        pad(dateObj.getMonth() + 1) + '/' +
                                        dateObj.getFullYear() + ' ' +
                                        pad(dateObj.getHours()) + ':' +
                                        pad(dateObj.getMinutes()) + ':' +
                                        pad(dateObj.getSeconds());
                    $(this).text(formattedDate);
                } else {
                    console.log('Invalid date:', rawDateStr);
                }
            }
        });
    });
    
    $(document).ready(function() {
        // Lấy param top từ URL, nếu không có thì mặc định 5
        const urlParams = new URLSearchParams(window.location.search);
        const currentTop = urlParams.get('top') || '5';
        $('#topSelect').val(currentTop);
    
        $('#topSelect').change(function() {
          const selectedTop = $(this).val();
          // Tạo URL mới với param top
          const newUrl = window.location.pathname + '?top=' + selectedTop;
          window.location.href = newUrl; // reload trang
        });
      });

})(jQuery);
