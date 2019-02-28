clear all;
load census;
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%% Este script gera imagens png dos graficos
%% o nome dos ficheiros tem que ser iguais aos nomes do array output
display_dots=1; %change it, 1->display dots;0->not display dots
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

output=["count_hit_data","count_miss_data","height_data","leaves_data"];
titles=["Std of number of calls on hit plot","Std of number of calls on miss plot","Std of maximum tree height plots","Std of number of leaves plots"];
ylabels=["std of calls on hit", "stf of calls on miss", "std of maximum height", "std of number of leaves"];
height_and_leaves="%d %d %d %f %f\n";
counts_hit_miss="%d %f %f\n";
format_type=counts_hit_miss;
divisor=3;
for i=1:length(output)
    file_name="";
    file_name=strcat(output(i),'.txt');
    file=fopen(file_name,"r");
    if (i>2)
        format_type=height_and_leaves;
        divisor=5;
    end
    A=fscanf(file,format_type);
    N=length(A);
    std=zeros(N/divisor,1);
    n_values=zeros(N/divisor,1);
    k=1;
    for j=1:divisor:N
         n_values(k)=A(j);
         std(k)=A(j+2+rem(divisor,3));
         k=k+1;
    end
    figure('Position',[0 0 1920 1080]); %change this to set resolution of graph
    hold on;
    grid on;
    plot(n_values,std,'LineWidth', 3, 'MarkerSize', 30);
    legend({"Std"}, 'FontSize', 20, 'Location', 'northwest');
    title(titles(i), 'FontSize', 22);
    xlabel("Number of nodes (n)", 'FontSize', 20);
    ylabel(ylabels(i), 'FontSize', 20);
    set(gca, 'FontSize', 20);
    output(i)=strcat(output(i),"_std");
    set(gcf,'PaperPositionMode','auto')
    print(output(i),'-dpng','-r0')
    fclose(file);
end

