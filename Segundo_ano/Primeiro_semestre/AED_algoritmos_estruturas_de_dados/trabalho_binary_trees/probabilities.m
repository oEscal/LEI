function probabilities(file_name, choice)
	file = fopen(file_name, "r");
	data = fscanf(file, "%d\n");

	prob_lin = zeros(1:length(data), 1);
	prob_bal = zeros(1:length(data), 1);
	if choice == 1
		for i = 1:length(data)
			prob_lin(i) = 2/factorial(data(i));
		end

		plot(data, prob_lin, '.-', 'LineWidth', 3, 'MarkerSize', 30);
	elseif choice == 2
		for i = 1:length(prob_lin)
			for n = 1:ceil(log2(data(i) + 1))
				prob_lin(i) = 2/factorial(data(i));
			end


		end
		plot(data, prob_lin);
	end

end
