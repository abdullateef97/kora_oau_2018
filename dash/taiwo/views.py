from django.shortcuts import render

# Create your views here.
from .models import Phone
from .forms import PhoneForm
from django.views.generic import TemplateView, CreateView, ListView
from django.contrib.auth import user_logged_in


class CreatePhoneView(CreateView):
    model = Phone
    template_name = 'create_phone.html'
    form_class = PhoneForm

    def form_valid(self, form):
        phone = form.save(commit=False)
        tweet.author = user_logged_in
        phone.save()
        return HttpResponseRedirect(reverse('home'))



class ListPhoneView(ListView):
    model = Phone
    template_name = 'home.html'
    context_object_name = 'phone_list'

    # def get_queryset(self):
    #     return Phone.objects.all()