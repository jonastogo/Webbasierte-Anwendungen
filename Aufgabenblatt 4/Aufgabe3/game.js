var posX = 268, posY = 168, moveDir = 0;

function attach(element, type, fn){
  if(element.addEventListener)
    element.addEventListener(type, fn, false);
  else if(element.attachEvent)
    element.attachEvent('on' + type, fn);
}

function paintStage(stageContext, bufferCanvas){
  stageContext.drawImage(bufferCanvas, 0, 0, 600, 400);
}

function loadImg(sCon, imgUrl,x,y,sx,sy,stageContext, bufferCanvas,callback){
  var imgBuffer = new Image();
  imgBuffer.src = imgUrl;
  imgBuffer.onload = function(){
    sCon.drawImage(imgBuffer,x,y,sx,sy);
    callback();
  };
}

function paintBackBuffer(stageContext, bufferCanvas, bufferContext, moveDir){
  bufferContext.clearRect(0,0,600,400);
  loadImg(bufferContext, 'runway.jpg', 0, 0, 600, 400, stageContext, bufferCanvas, function(){
    loadImg(bufferContext, 'buggy_'+moveDir+'.gif', posX, posY, 64, 64, stageContext, bufferCanvas, function(){
      paintStage(stageContext,bufferCanvas);
    });
  });
}

window.onload = function(){
  var stageCanvas = document.getElementById('stage'),
      stageContext = stageCanvas.getContext('2d'),
      bufferCanvas = document.getElementById('buffer'),
      bufferContext = bufferCanvas.getContext('2d');
  attach(document, 'keypress', function(e){
    switch(e.keyCode){
      case 37:
      case 97:
        moveDir = 6;
        posX -= 5;
        break;
      case 38:
      case 119:
        moveDir = 0;
        posY -= 5;
        break;
      case 39:
      case 100:
        moveDir = 2;
        posX += 5;
        break;
      case 30:
      case 115:
        moveDir = 4;
        posY += 5;
        break;
    }
    paintBackBuffer(stageContext,bufferCanvas, bufferContext, moveDir);
  });
  paintBackBuffer(stageContext,bufferCanvas, bufferContext, moveDir);
}
