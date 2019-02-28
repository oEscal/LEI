%tic
%file = csvread("handwritten_data_785.csv");
%toc
for i = 12:12
	[cols, rows] = find(file(:, 1) == i - 1);
%	cols(1)
	x = find(file(cols(105), 2:end) > 0)/28;
	y = mod(find(file(cols(105), 2:end) > 0), 28);
	plot(y(end:-1:1), x, '*');
	xlim([1 28]);
	ylim([1 28]);
end
