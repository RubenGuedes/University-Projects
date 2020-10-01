function main()
{
	var canvas = document.getElementById("myCanvas").getContext("2d");
	canvas.fillStyle = "steelblue";
	canvas.fillRect(0,0,500,500);
	canvas.translate(250,250);
	quadrado(canvas);
}

function quadrado(q)
{
	q.rotate(Math.PI/4);
	q.fillStyle = "green";
	q.rect(-50,-50,100,100);
	q.fill();
	return q;
}
