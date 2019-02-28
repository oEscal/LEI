close all;
clc;

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%% Este script gera imagens png dos graficos
%% o nome dos ficheiros tem que ser iguais aos nomes do array output
display_dots=1; %change it, 1->display dots;0->not display dots
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

output=["count_hit_data","count_miss_data","height_data","leaves_data"];
titles=["Count on hit","Count on miss","Height","Leaves"];
height_and_leaves="%d %d %d %f %f\n";
counts_hit_miss="%d %f %f\n";
format_type=counts_hit_miss;
divisor=3;
for i=1:length(output)
file_name="";
file_name=strcat('../',output(i),'.txt');
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
   D=[n_values,1+0*n_values];
else
    D=[log(n_values),1+0*n_values];
end
w=pinv(D)*average;

if(i>2)
    if(display_dots==1)
    plot(n_values,min,".");
    end
    w_min=pinv(D)*min;
    plot(n_values,D*w_min);
end
if(display_dots==1)
plot(n_values,average,".");
end
plot(n_values,D*w);
if(i>2)
    w_max=pinv(D)*max;
    if(display_dots==1)
    plot(n_values,max,".");
    end
    plot(n_values,D*w_max);
    if (display_dots==1)
    legend("Min","Fit min","mean","Fit mean","Max","Fit max");
    else
        legend("Fit min","Fit mean","Fit max");
    end
    
else
    if(display_dots==1)
    legend("mean","Fit mean");
    else
        legend("Fit mean");
    end
end
title(titles(i));
xlabel("Number of nodes (n)");
ylabel("IDK o que meter aqui");
set(gcf,'PaperPositionMode','auto')
print(output(i),'-dpng','-r0')
fclose(file);
end

