var upfiles = new Array();
// getElementById
function $id(id) {
return document.getElementById(id);
}
// output information
function Output(msg) {
var m = $id("messages");
m.innerHTML = msg + m.innerHTML;
}
// file drag hover
function FileDragHover(e) {
e.stopPropagation();
e.preventDefault();
e.target.className = (e.type == "dragover" ? "hover" : "");
}
// file selection
function FileSelectHandler(e) {
// cancel event and hover styling
FileDragHover(e);
// fetch FileList object
var files = e.target.files || e.dataTransfer.files;
// process all File objects
for ( var i = 0, f; f = files[i]; i++) {
ParseFile(f);
upfiles.push(f);
}
}
// output file information
function ParseFile(file) {
Output("<p>文件信息: <strong>" + file.name
+ "</strong> 类型: <strong>" + file.type
+ "</strong> 大小: <strong>" + file.size
+ "</strong> bytes</p>");
}
function upLoad() {
if (upfiles[0]) {
var xhr = new XMLHttpRequest(); //Ajax异步传输数据
xhr.open("POST", "UploadServlet", true);
var formData = new FormData();
for ( var i = 0, f; f = upfiles[i]; i++) {
formData.append('myfile', f);
}
xhr.send(formData);
xhr.onreadystatechange=function(e){
history.go(0); //由于这个页面还要显示可以下载的文件，所以需要刷新下页面
}
return false;
}
}
// initialize
function Init() {
var fileselect = $id("fileselect"), filedrag = $id("filedrag"), submitbutton = $id("submitbutton");
// file select
fileselect.addEventListener("change", FileSelectHandler, false);
// is XHR2 available?
var xhr = new XMLHttpRequest();
if (xhr.upload) {
// file drop
filedrag.addEventListener("dragover", FileDragHover, false);
filedrag.addEventListener("dragleave", FileDragHover, false);
filedrag.addEventListener("drop", FileSelectHandler, false);
filedrag.style.display = "block";
// remove submit button
//submitbutton.style.display = "none";
}
}
// call initialization file
if (window.File && window.FileList && window.FileReader) {
Init();
}