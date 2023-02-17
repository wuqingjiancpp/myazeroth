$(".nav-link").click(function () {
    $(".nav-link").removeClass("active");
    $(this).addClass("active");
});
$.getJSON( "/user", function( data ) {
    let username = data.username;
    $( "#username" ).html(username);
});