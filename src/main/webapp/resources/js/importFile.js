function downloadCourse() {
    var selectBox = document.getElementById("selectBox");
    var selectedValue = selectBox.options[selectBox.selectedIndex].value;
    window.location = "/download" + "?courseName=" + selectedValue;
}