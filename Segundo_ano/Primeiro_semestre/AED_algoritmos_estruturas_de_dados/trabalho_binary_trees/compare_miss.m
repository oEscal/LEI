function compare_miss(file_name)
   file = fopen(file_name, "r");
   data = fscanf(file, "%d %f %f\n");

   max_nodes_per_level = zeros(1, 14);
   max_nodes_per_level(1) = 1;
   max_nodes_per_level(2) = 2;

   for i = 3:length(max_nodes_per_level)
      max_nodes_per_level(i) = max_nodes_per_level(i - 1)*2;
   end

   ns = data(1:3:end);
   cs = cumsum(max_nodes_per_level);
   miss_theor_min = zeros(length(ns), 1);
   miss_theor_max = zeros(length(ns), 1);
   for i = 1:length(miss_theor_min)
      cs_max = cumsum([1:ns(i)]);
      miss_theor_max(i) = cs_max(end);
      for n = 1:length(max_nodes_per_level)
         if  ns(i) <= cs(n)
            miss_theor_min(i) = (max_nodes_per_level(n) - ns(i) + cs(n - 1))*n;
            miss_theor_min(i) = miss_theor_min(i) + (ns(i) - cs(n - 1))*2*(n + 1);
            break;
         end
      end
   end

   plot(ns(1:end), data(2:3:end), '.', 'MarkerSize', 30);
   hold on;
   plot(ns(1:end), miss_theor_min(1:end)./ns(1:end), '.-', 'LineWidth', 3, 'MarkerSize', 30);
   hold on;
   plot(ns(1:7), miss_theor_max(1:7)./ns(1:7), '.-', 'LineWidth', 3, 'MarkerSize', 30);
   title("Pratical vs theorerical number of calls on miss", 'FontSize', 22);
   xlabel("Number of nodes (n)", 'FontSize', 20);
   ylabel("Number of calls on hit", 'FontSize', 20);
   legend({"Pratical result", "Theoretical expectation of min number of calls", "Theoretical expectation of max number of calls"}, 'FontSize', 20, 'Location', 'southeast');
   set(gca, 'FontSize', 20);
end
