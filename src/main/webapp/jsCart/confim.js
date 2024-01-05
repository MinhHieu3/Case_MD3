function confirmAction() {
    var xacNhan = confirm("Đồng Ý Thanh Toán ?");
    if (xacNhan) {
      window.location.href="/home?action=order"
    } else {
       window.location.href="http://localhost:8080/home?action=cart"
    }
}

