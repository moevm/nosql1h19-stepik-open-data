function getStatistics() {
    var selectBox = document.getElementById("selectBox");
    var selectedValue = selectBox.options[selectBox.selectedIndex].value;
    window.location = "/statistics" + "?courseName=" + selectedValue;
}