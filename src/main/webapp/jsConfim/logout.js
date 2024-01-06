function confirmAction() {
    var xacNhan = confirm("Bạn Muốn Đăng Xuất ?");
    if (xacNhan) {
        window.location.href="http://localhost:8080/homePage"
    } else {
        window.location.href="http://localhost:8080/home?action=showList"
    }
}

