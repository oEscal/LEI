function compare_heights(file_name, last_n_index)
   file = fopen(file_name, "r");
   data = fscanf(file, "%d %d %d %f %f\n");

   plot(data(1:5:last_n_index*5), data(3:5:last_n_index*5), '.', 'MarkerSize', 30);
   hold on;
   plot([data(1):0.01:data(last_n_index*5 + 1)], floor(([data(1):0.01:data(last_n_index*5 + 1)] + 1)/2), 'LineWidth', 3);
   title("Pratical vs theorerical max number of leaves", 'FontSize', 22);
   xlabel("Number of nodes (n)", 'FontSize', 20);
   ylabel("Number of leaves", 'FontSize', 20);
   legend({"Pratical result", "Theoretical expectation"}, 'FontSize', 20, 'Location', 'southeast');
   set(gca, 'FontSize', 20);
end