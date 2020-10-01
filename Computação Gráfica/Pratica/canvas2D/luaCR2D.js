function main()
{
	var c2d = document.getElementById("acanvas").getContext("2d");
	c2d.fillStyle = "black";
	c2d.fillRect(0,0,500,500);

	moon(c2d);
}
function moon(context)
{
	var part1 = new moon_part1();
	var part2 = new moon_part2();
	
	context.fillStyle = "steelblue";
	context.fill(part1);
	context.stroke(part1);
	
	context.fillStyle = "black";
	context.fill(part2);
	context.stroke();
}
function moon_part1()
{
	var p1 = new Path2D();
	var centerX = 375;
	var centerY = 250;
	var radiusX = 250;
	var radiusY = 150;
	p1.ellipse(centerX,centerY,radiusX,radiusY,0, Math.PI/2 , (3*Math.PI)/2);
	return p1;
}
function moon_part2()
{
	var p2 = new Path2D();
	var centerX = 375;
	var centerY = 250;
	var radius = 150;
	p2.arc(centerX,centerY,radius, Math.PI/2 , (3*Math.PI)/2);
	return p2
}
