<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<title >Lista de Presença</title>
</head>
<body>

  <form action="/gerenciador/TestString" method="post" enctype = "multipart/form-data">
  

<style type="text/css">
	div.b {
		text-align: center;
		}
	div.c {
		text-align: justify-all;
		}
	body, html{
		height: 100%
	}
	div.d {
		background-image: url(saojudas.jpg);
		height: 100%;
		background-position: center;
		background-repeat: no-repeat;
		background-size: cover;
	}

</style>
<div class="d">
<div class="b">
<h1 style="font-style: inherit;text-decoration: blink; color: #fefeff" >Posicione-se na camera.</h1>
</div>
<div class="c">
<video id="player" controls autoplay></video>
<button id="capture"  type="button" class="btn btn-success">Marcar Presença! </button> 
<input type="submit" name="img" id="img" value="envia" />

<a href="bem-vindo.jsp"><button type="button" class="btn btn-dark">Back</button></a>
<canvas id="snapshot" width=320 height=240></canvas>

<script
  src="https://code.jquery.com/jquery-3.4.1.js"
 ></script>
  
<script>
  var player = document.getElementById('player');
  var snapshotCanvas = document.getElementById('snapshot');
  var captureButton = document.getElementById('capture');
  var img;
  var handleSuccess = function (stream) {
    // Attach the video stream to the video element and autoplay.
    player.srcObject = stream;
  };

  captureButton.addEventListener('click', function() {
    var context = snapshot.getContext('2d');
    // Draw the video frame to the canvas.
    context.drawImage(player, 0, 0, snapshotCanvas.width,
        snapshotCanvas.height);
    
     img = snapshotCanvas.toDataURL("image/jpg");
     //console.log(img);
   
   // enviar();
    eviar2();
  });

 // img = snapshotCanvas.toDataURL("image/jpg");
  
  navigator.mediaDevices.getUserMedia({video: true})
      .then(handleSuccess);
  
  function enviar(){
	  var fd = new FormData();
	  fd.append('arquivo',img);
	  $.ajax({
			url: "/gerenciador/registro",
			type: "POST",
			data: fd,
			contentType: false,
			cache: false,
			processData:false,
			success: function(data){
				
			},
			error: function(){} 	 
		});
  }
  
  function base64ToBlob(base64, mime) 
  {
      mime = mime || '';
      var sliceSize = 1024;
      var byteChars = window.atob(base64);
      var byteArrays = [];

      for (var offset = 0, len = byteChars.length; offset < len; offset += sliceSize) {
          var slice = byteChars.slice(offset, offset + sliceSize);

          var byteNumbers = new Array(slice.length);
          for (var i = 0; i < slice.length; i++) {
              byteNumbers[i] = slice.charCodeAt(i);
          }

          var byteArray = new Uint8Array(byteNumbers);

          byteArrays.push(byteArray);
      }

      return new Blob(byteArrays, {type: mime});
  }
  function eviar2(){
	 
	  var url = "/gerenciador/registro";                
	  //var image = $('#img').val();
	  var base64ImageContent = img.replace(/^data:image\/(png|jpg);base64,/, "");
	  //console.log(base64ImageContent);
	  
	  $.ajax({
	      url: "/gerenciador/TestString",
	      type: "POST", 
	      
	      data: {arquivo: base64ImageContent},
	      success: function(a){
	    	//alert(a);  
	      }
	  });
	  return;
	  var blob = base64ToBlob(base64ImageContent, 'image/jpg');                
	  var formData = new FormData();
	  formData.append('arquivo', blob);

	  $.ajax({
	      url: url, 
	      type: "POST", 
	      cache: false,
	      contentType: false,
	      processData: false,
	      data: formData})
	          .done(function(e){
	              alert('done!');
	          });
	  
	  
	  
  }
  
  
  
</script>

</div> 
</div>
</form>
</body>
</html>