function main() {
	var c2d = document.getElementById("acanvas").getContext("2d");
	c2d.fillStyle = "steelblue";
	c2d.fillRect(0,0,256,256);
	curve(c2d);
}
function curve(c)
{
	var linha = new Path2D();
	linha.lineTo(64,64);
	linha.arcTo(0,0,64,16,16);
	c.stroke(linha);
}
