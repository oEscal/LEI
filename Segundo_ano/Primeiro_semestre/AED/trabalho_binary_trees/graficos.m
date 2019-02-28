clear all;

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%% Este script gera imagens png dos graficos
%% o nome dos ficheiros tem que ser iguais aos nomes do array output
display_dots=1; %change it, 1->display dots;0->not display dots
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

format_s="%.5f*log(n) +(%.5f)";
output=["count_hit_data","count_miss_data","height_data","leaves_data"];
titles=["Number of calls on hit plot","Number of calls on miss plot","Maximum tree height plots","Number of leaves plots"];
ylabels=["calls on hit", "calls on miss", "maximum height", "number of leaves"];
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
    average=zeros(N/divisor,1);
    std=zeros(N/divisor,1);
    n_values=zeros(N/divisor,1);
    if(i>2)
        max=zeros(N/divisor,1);
        min=zeros(N/divisor,1);  
    end
    k=1;
    for j=1:divisor:N
         n_values(k)=A(j);
         average(k)=A(j+1+rem(divisor,3)); 
         std(k)=A(j+2+rem(divisor,3));   
        if(i>2)
            min(k)=A(j+1);
            max(k)=A(j+2);
        end
         k=k+1;
    end
    figure('Position',[0 0 1920 1080]); %change this to set resolution of graph
    hold on;
    if(i==4)
        format_s="%.5f*n +(%.5f)";
       D=[n_values,1+0*n_values];
    else
        D=[log(n_values),1+0*n_values];
    end
    w=pinv(D)*average;

    if(i>2)
        if(display_dots==1)
        plot(n_values,min,".", 'LineWidth', 3, 'MarkerSize', 30);
        end
        w_min=pinv(D)*min;
        plot(n_values,D*w_min, 'LineWidth', 3, 'MarkerSize', 30);
    end
    if(display_dots==1)
    plot(n_values,average,".", 'LineWidth', 3, 'MarkerSize', 30);
    end
    plot(n_values,D*w, 'LineWidth', 3, 'MarkerSize', 30);
    if(i>2)
        w_max=pinv(D)*max;
        if(display_dots==1)
        plot(n_values,max,".", 'LineWidth', 3, 'MarkerSize', 30);
        end
        plot(n_values,D*w_max, 'LineWidth', 3, 'MarkerSize', 30);
        if (display_dots==1)
        legend({"Min",sprintf(format_s,w_min(1),w_min(2)),"mean",sprintf(format_s,w(1),w(2)),"Max",sprintf(format_s,w_max(1),w_max(2))}, 'FontSize', 20, 'Location', 'southeast');
        else
            legend({sprintf(format_s,w_min(1),w_min(2)),sprintf(format_s,w(1),w(2)),sprintf(format_s,w_max(1),w_max(2))}, 'FontSize', 20, 'Location', 'southeast');
        end
    else
        if(display_dots==1)
        legend({"mean",sprintf(format_s,w(1),w(2))}, 'FontSize', 20, 'Location', 'southeast');
        else
            legend(sprintf(format_s,w(1),w(2)), 'FontSize', 20, 'Location', 'southeast');
        end
    end
    ylim([0 inf]);
    title(titles(i), 'FontSize', 22);
    xlabel("Number of nodes (n)", 'FontSize', 20);
    ylabel(ylabels(i), 'FontSize', 20);
    set(gca, 'FontSize', 20);

    set(gcf,'PaperPositionMode','auto')
    print(output(i),'-dpng','-r0')
    fclose(file);
end

