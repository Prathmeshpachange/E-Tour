using AutoMapper;
using SafarnamaApplication.DTOs;
using SafarnamaApplication.Models;
using SafarnamaApplication.Repositories;

namespace SafarnamaApplication.Services
{
    public class CategoryService : ICategoryService
    {
        private readonly ICategoryRepository _categoryRepository;
        private readonly IPackageRepository _packageRepository;
        private readonly IMapper _mapper;

        public CategoryService(ICategoryRepository categoryRepository, IMapper mapper, IPackageRepository packageRepository)
        {
            _categoryRepository = categoryRepository;
            _mapper = mapper;
            _packageRepository = packageRepository;

        }

        public async Task<IEnumerable<CategoryDTO>> GetAllCategoriesAsync()
        {
            var categories = await _categoryRepository.GetAllAsync();
            return _mapper.Map<IEnumerable<CategoryDTO>>(categories);
        }

        public async Task<CategoryDTO?> GetCategoryByIdAsync(int id)
        {
            var category = await _categoryRepository.GetByIdAsync(id);
            return category == null ? null : _mapper.Map<CategoryDTO>(category);
        }

        public async Task<CategoryDTO> CreateCategoryAsync(CategoryDTO categoryDTO)
        {
            var entity = _mapper.Map<Category>(categoryDTO);
            var created = await _categoryRepository.AddAsync(entity);
            return _mapper.Map<CategoryDTO>(created);
        }

        public async Task<CategoryDTO?> UpdateCategoryAsync(int id, CategoryDTO categoryDTO)
        {
            var existing = await _categoryRepository.GetByIdAsync(id);
            if (existing == null) return null;

            _mapper.Map(categoryDTO, existing); // maps updated fields into existing object

            var updated = await _categoryRepository.UpdateAsync(existing);
            return _mapper.Map<CategoryDTO>(updated);
        }

        public async Task<bool> DeleteCategoryAsync(int id)
        {
            return await _categoryRepository.DeleteAsync(id);
        }

        public async Task<IEnumerable<PackageDTO>> GetPackagesByCategoryAsync(int categoryId)
        {
            var packages = await _packageRepository.GetPackagesByCategoryAsync(categoryId);
            return packages.Select(p => new PackageDTO
            {
                PackageId = p.PackageId,
                PackageName = p.PackageName,
                PackageInfo = p.PackageInfo,
                PackageImagePath = p.PackageImagePath,
                DurationDays = p.DurationDays,
                StartDate = p.StartDate,
                EndDate = p.EndDate,
                CategoryId = p.CategoryId,
                SubcategoryId = p.SubcategoryId
            });
        }
    }
}
