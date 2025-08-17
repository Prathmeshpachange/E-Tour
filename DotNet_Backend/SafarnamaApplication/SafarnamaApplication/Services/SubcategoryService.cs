using AutoMapper;
using SafarnamaApplication.DTOs;
using SafarnamaApplication.Models;
using SafarnamaApplication.Repositories;

namespace SafarnamaApplication.Services
{
    public class SubcategoryService : ISubcategoryService
    {
        private readonly ISubcategoryRepository _subcategoryRepository;
        private readonly IMapper _mapper;

        public SubcategoryService(ISubcategoryRepository subcategoryRepository, IMapper mapper)
        {
            _subcategoryRepository = subcategoryRepository;
            _mapper = mapper;
        }

        public async Task<IEnumerable<SubcategoryDTO>> GetAllSubcategoriesAsync()
        {
            var subcategories = await _subcategoryRepository.GetAllAsync();
            return _mapper.Map<IEnumerable<SubcategoryDTO>>(subcategories);
        }

        public async Task<IEnumerable<SubcategoryDTO>> GetByCategoryIdAsync(int categoryId)
        {
            var subcategories = await _subcategoryRepository.GetByCategoryIdAsync(categoryId);
            return _mapper.Map<IEnumerable<SubcategoryDTO>>(subcategories);
        }
    }
}
