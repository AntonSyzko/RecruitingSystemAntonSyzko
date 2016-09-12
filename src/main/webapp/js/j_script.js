function openbox(id) {
    var div = document.getElementById(id);
    if (div.style.display == 'block') {
        div.style.display = 'none';
    }
    else {
        div.style.display = 'block';
    }
}

function comment_like(id) {
    jQuery.ajax({
        url: "/like_comment.html?comment_id=" + id + "",
        cache: false,
        dataType: "text",
        success: function (data) {

        }
    });
}

function add_favorites(id) {
    jQuery.ajax({
        url: "/add_favorite.html?book_id=" + id + "",
        cache: false,
        dataType: "html",
        success: added()
    });
}

function delete_favorites(id) {
    jQuery.ajax({
        url: "/delete_favorite.html?book_id=" + id + "",
        cache: false,
        dataType: "html",
        success: deleted()
    });
}



function Open_desc(id, id2) {
    var div = document.getElementById(id);
    div.style.display = 'block';
    var div2 = document.getElementById(id2);
    div2.style.boxShadow = '0 0 10px #30332d';
    div2.style.padding = '10px';

}
function Close_desc(id, id2) {
    var div = document.getElementById(id);
    div.style.display = 'none';
    var div2 = document.getElementById(id2);
    div2.style.boxShadow = '0 0 0px rgba(0, 0, 0, 0)';
    div2.style.padding = '0'
}

function add_product_to_cart(productId) {
    jQuery.ajax({
        url: '/add_product_in_cart.html?product_id=' + productId + '',
        cache: false,
        dataType: "text",
        success: function (data) {
            jQuery("#cart").empty();
            var j = JSON.parse(data);
            console.log('success');

            var count = '<table style="height: 100%;width: 100%">' +
                '<tr style="height: 33%">' +
                '<td>' +
                '<strong><span style="font-size:12px"><span style="color:#FFFFFF">&nbsp;&nbsp;&nbsp;In cart ' + j.countProductsInOrder + ' goods</span></span></strong>' +
                '</td>' +
                '</tr>' +
                '<tr style="height: 33%;width: 100%">' +
                '<td style="border-radius: 4px" bgcolor="#00A01D" align=center valign=center>' +
                '<strong><span style="font-size:12px"><span style="color:#FFFFFF"><a href="/cart.html" style="color: #ffffff">Checkout</a></span></span></strong>' +
                '</td>' +
                '</tr>' +
                '<tr style="height: 33%">' +
                '<td>' +
                '<strong><span style="font-size:12px"><span style="color:#FFFFFF">&nbsp;&nbsp;&nbsp;Amount of ' + j.totalSum + ' USD</span></span></strong>' +
                '</td>' +
                '</tr>' +
                '</table>';
            jQuery("#cart").html(count);
        }
    });
}
function load_cart() {
    jQuery.ajax({
        url: '/load_cart.html',
        cache: false,
        dataType: "text",
        success: function (data) {
            jQuery("#cart").empty();
            var j = JSON.parse(data);
            console.log('success');

            var count = '<table style="height: 100%;width: 100%">' +
                '<tr style="height: 33%">' +
                '<td>' +
                '<strong><span style="font-size:12px"><span style="color:#FFFFFF">&nbsp;&nbsp;&nbsp;In cart ' + j.countProductsInOrder + ' goods</span></span></strong>' +
                '</td>' +
                '</tr>' +
                '<tr style="height: 33%;width: 100%">' +
                '<td style="border-radius: 4px" bgcolor="#00A01D" align=center valign=center>' +
                '<strong><span style="font-size:12px"><span style="color:#FFFFFF"><a href="/cart.html" style="color: #ffffff">Checkout</a></span></span></strong>' +
                '</td>' +
                '</tr>' +
                '<tr style="height: 33%">' +
                '<td>' +
                '<strong><span style="font-size:12px"><span style="color:#FFFFFF">&nbsp;&nbsp;&nbsp;Amount of ' + j.totalSum + ' USD</span></span></strong>' +
                '</td>' +
                '</tr>' +
                '</table>';
            jQuery("#cart").html(count);
        }
    });
}
function open_picture(productId) {
    var div = document.getElementById('picture');
    div.style.display = 'block';
    var image = '<a href="#" onclick="close_picture()"  ><img src="/load_img/'+productId+'"></a>';
    console.log(productId);
    jQuery("#picture").empty();
    jQuery("#picture").html(image);

}
function close_picture() {
    var div = document.getElementById('picture');
    div.style.display = 'none';
}