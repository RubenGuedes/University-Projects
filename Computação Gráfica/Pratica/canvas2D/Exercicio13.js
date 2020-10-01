function main()
{
	var ctx = document.getElementById("myCanvas").getContext("2d");
	//grelha_s(ctx);
	tabuleiro(ctx, 80, 80, "red", "black");
}
function tabuleiro(c, ncols, nrows, color1, color2)
{
	var len = 50;

	for(var cols = 0; cols < ncols; cols++)
	{
		for(var rows = 0; rows < nrows; rows++)
		{
			if (( cols + rows ) % 2)
			{
				c.fillStyle = color1;
			}
			else
			{
				c.fillStyle = color2;
			}
			c.fillRect(rows * len, cols * len, len, len);
		}
	} 	
}
function grelha_s(ctx)
{
	var SQUARE_len = 50;
	for(var i = 0; i < 8; i++)
	{
		for(var v = 0; v < 8; v++)
		{
			if( (i + v) % 2 == 0)
			{
				ctx.fillStyle = "black";
			}
			else
			{
				ctx.fillStyle = "white";
			}	
			ctx.fillRect(i * SQUARE_len, v * SQUARE_len, SQUARE_len, SQUARE_len);
		}
	}
}
