var input;
var option;
var outputmm3;
var outputcm3;
var outputdm3;
var outputm3;
var outputl;

function calculation(){
  	input = document.getElementById("input").value;
    option = document.getElementById("unit");
    outputmm3 = document.getElementById("outputmm3").value;
    outputcm3 = document.getElementById("outputcm3").value;
    outputdm3 = document.getElementById("outputdm3").value;
    outputm3 = document.getElementById("outputm3").value;
    outputl = document.getElementById("outputl").value;
    var currentOption = option.options[option.selectedIndex].value;
    switch(currentOption){
      case "mm3":
        outputmm3 = input;
        outputcm3 = input/1000;
        outputdm3 = input/1000000;
        outputm3 = input/1000000000;
        outputl = input/1000000;
        break;
      case "cm3":
        outputmm3 = input*1000;
        outputcm3 = input;
        outputdm3 = input/1000;
        outputm3= input/1000000;
        outputl = input/1000;
        break;
      case "dm3":
        outputmm3 = input*1000000;
        outputcm3 = input*1000;
        outputdm3 = input;
        outputm3 = input/1000;
        outputl = input;
        break;
      case "m3":
        outputmm3 = input*1000000000;
        outputcm3 = input*1000000;
        outputdm3 = input*1000;
        outputm3 = input;
        outputl = input*1000;
        break;
      case "l (Liter)":
        outputmm3 = input*1000000;
        outputcm3 = input*1000;
        outputdm3 = input;
        outputm3 = input/1000;
        outputl = input;
        break;
    }

    document.getElementById("outputmm3").value = outputmm3;
    document.getElementById("outputcm3").value = outputcm3;
    document.getElementById("outputdm3").value = outputdm3;
    document.getElementById("outputm3").value = outputm3;
    document.getElementById("outputl").value = outputl;
}
